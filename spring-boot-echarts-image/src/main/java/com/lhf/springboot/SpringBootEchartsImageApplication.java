package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootEchartsImageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEchartsImageApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8095/swagger-ui.html");
    }

}
