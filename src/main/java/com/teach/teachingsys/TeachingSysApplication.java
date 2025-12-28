package com.teach.teachingsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TeachingSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachingSysApplication.class, args);
    }

}
