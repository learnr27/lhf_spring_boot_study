package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootSqlserverApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(SpringBootSqlserverApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSqlserverApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8088/swagger-ui.html");
    }

}
