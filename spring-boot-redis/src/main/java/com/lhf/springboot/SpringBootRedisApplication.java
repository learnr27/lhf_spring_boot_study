package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRedisApplication {

    public static void main(String[] args) {
        System.getProperties().put("projectName", "springApp");
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

}
