package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootElasticsearch1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearch1Application.class, args);
        System.out.println("Swagger文档：http://localhost:8066/swagger-ui.html");
    }

}
