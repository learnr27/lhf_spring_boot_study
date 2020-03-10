package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  //开启缓存
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}
