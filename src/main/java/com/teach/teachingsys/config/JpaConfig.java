package com.teach.teachingsys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.teach.teachingsys.repository")
public class JpaConfig {
}

