package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMultiMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiMongodbApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8092/swagger-ui.html");
    }

}
