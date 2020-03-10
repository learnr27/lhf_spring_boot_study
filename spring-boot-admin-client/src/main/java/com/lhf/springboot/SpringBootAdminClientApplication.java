package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootAdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminClientApplication.class, args);
        System.out.println("访问地址：http://localhost:8094/");
    }

    //    @Configuration
//    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll()
//                    .and().csrf().disable();
//        }
//    }

}
