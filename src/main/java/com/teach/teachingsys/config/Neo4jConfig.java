package com.teach.teachingsys.config;

import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(
        basePackages = "com.teach.teachingsys.graph.repository",
        transactionManagerRef = "neo4jTransactionManager"
)
public class Neo4jConfig {

    /**
     * 1. 显式创建 Neo4j 事务管理器
     */
    @Bean("neo4jTransactionManager")
    public PlatformTransactionManager neo4jTransactionManager(Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
        return new Neo4jTransactionManager(driver, databaseSelectionProvider);
    }

    /**
     * 2. 显式创建 Neo4jTemplate
     * 修复点：这里必须传入 Neo4jMappingContext，否则会报编译错误
     * 同时传入 neo4jTransactionManager，解决 "txTemplate is null" 的运行时错误
     */
    @Bean
    public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient,
                                       Neo4jMappingContext neo4jMappingContext,
                                       PlatformTransactionManager neo4jTransactionManager) {
        return new Neo4jTemplate(neo4jClient, neo4jMappingContext, neo4jTransactionManager);
    }
}