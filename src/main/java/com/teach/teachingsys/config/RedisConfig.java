package com.teach.teachingsys.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RedisConfig implements CachingConfigurer {

    /**
     * 自定义 Redis 序列化器，解决 LocalDateTime 和 Hibernate 代理对象问题
     */
    private GenericJackson2JsonRedisSerializer jsonSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 1. 解决 Java 8 时间类型序列化问题 (解决你报错的核心)
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 2. 解决 Hibernate 延迟加载导致的 Proxy 序列化报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 3. 忽略在 JSON 中存在但 Java 对象中不存在的属性，防止报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 4. 开启自动类型支持
        // 必须开启这个，否则 Redis 存的 JSON 不带类型信息，反序列化回 Optional 或 Page 对象时会报错
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                // 使用我们自定义的 JSON 序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer()));
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultConfig = cacheConfiguration();

        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        // 为不同业务模块定义不同的过期时间 (TTL)
        configMap.put("course", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        configMap.put("user", defaultConfig.entryTtl(Duration.ofMinutes(30)));
        configMap.put("questionBank", defaultConfig.entryTtl(Duration.ofMinutes(60)));
        configMap.put("exam", defaultConfig.entryTtl(Duration.ofMinutes(30)));
        configMap.put("system", defaultConfig.entryTtl(Duration.ofHours(24)));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(configMap)
                .transactionAware() // 开启事务支持（数据库回滚时缓存也会保持同步）
                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis 缓存读取异常 [Key: {}, Cache: {}]: {}", key, cache.getName(), exception.getMessage());
                // 异常降级：读取失败则直接透传，后续逻辑会去查数据库
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("Redis 缓存写入异常 [Key: {}, Cache: {}]: {}", key, cache.getName(), exception.getMessage());
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                log.error("Redis 缓存清除异常 [Key: {}, Cache: {}]: {}", key, cache.getName(), exception.getMessage());
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                log.error("Redis 缓存清空异常 [Cache: {}]: {}", cache.getName(), exception.getMessage());
            }
        };
    }
}