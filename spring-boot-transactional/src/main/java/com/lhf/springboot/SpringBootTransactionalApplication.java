package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTransactionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTransactionalApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8088/swagger-ui.html");
        System.out.println("Druid监控：http://localhost:8088/druid/index.html");
    }

}
