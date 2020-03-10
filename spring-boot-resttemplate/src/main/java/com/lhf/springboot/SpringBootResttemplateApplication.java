package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootResttemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootResttemplateApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8096/swagger-ui.html");
    }

}
