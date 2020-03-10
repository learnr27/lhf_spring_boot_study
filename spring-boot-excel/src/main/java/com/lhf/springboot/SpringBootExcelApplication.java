package com.lhf.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.springboot.mapper")
public class SpringBootExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExcelApplication.class, args);
    }

}
