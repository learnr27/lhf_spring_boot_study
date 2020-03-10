package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearchApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8084/swagger-ui.html");
    }

}
