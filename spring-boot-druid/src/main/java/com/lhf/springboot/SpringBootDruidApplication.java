package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDruidApplication.class, args);
        System.out.println("Druid数据源监控： http://localhost:9010/druid/login.html");
    }

}
