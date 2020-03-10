package com.lhf.springboot;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
public class SpringBootMybatisPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPagehelperApplication.class, args);
        System.out.println("Swagger文档：http://localhost:8090/swagger-ui.html");
        System.out.println("Druid数据源：http://localhost:8090/druid/index.html  账号：admin, 密码：123456");
    }

}
