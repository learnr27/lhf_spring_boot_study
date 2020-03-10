package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringBootAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAngularApplication.class, args);
    }

}
