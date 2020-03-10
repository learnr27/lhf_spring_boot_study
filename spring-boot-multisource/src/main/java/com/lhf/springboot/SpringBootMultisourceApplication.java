package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMultisourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultisourceApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8094/swagger-ui.html");
    }

}
