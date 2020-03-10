package com.lhf.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lhf.springboot.mapper")
@SpringBootApplication
public class SpringBootMybatisReadandwriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisReadandwriteApplication.class, args);
        System.out.println("swagger文档：http://localhost:8090/swagger-ui.html");
    }

}
