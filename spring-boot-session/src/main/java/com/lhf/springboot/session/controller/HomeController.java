package com.lhf.springboot.session.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: HomeController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2020/3/12 11:40
 */
@RestController
public class HomeController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/set")
    public String setSession(HttpSession session){
        session.setAttribute("login-user", "admin-123456");
        System.out.println("设置session.........");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String getSession(HttpSession session){
        String sess = session.getAttribute("login-user").toString();
        System.out.println("获取session: " + sess);
        return session.getAttribute("login-user") + " : " + port ;
    }
}
