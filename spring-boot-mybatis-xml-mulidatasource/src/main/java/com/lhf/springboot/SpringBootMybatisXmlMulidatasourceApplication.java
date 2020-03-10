package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMybatisXmlMulidatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisXmlMulidatasourceApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8098/swagger-ui.html");
    }

}
