package com.lhf.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.springboot.repository")
public class SpringBootVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueApplication.class, args);
    }

}
