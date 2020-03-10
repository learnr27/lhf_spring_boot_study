package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRedisCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisCrudApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8088/swagger-ui.html");
    }

}
