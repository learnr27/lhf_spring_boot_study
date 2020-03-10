package com.lhf.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lhf.springboot.mapper")
public class SpringBootShiroJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroJwtApplication.class, args);
    }

}
