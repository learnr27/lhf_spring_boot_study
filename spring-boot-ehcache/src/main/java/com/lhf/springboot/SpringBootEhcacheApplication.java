package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching  //开启Spring Boot应用程序缓存功能
@SpringBootApplication
public class SpringBootEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEhcacheApplication.class, args);
    }

}
