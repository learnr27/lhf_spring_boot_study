package com.lhf.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: RouterController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 12:37
 */
@Controller
public class RouterController {

    @GetMapping("login.html")
    public String login() {
        return "login";
    }

    @GetMapping("index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String _login() {
        return "login";
    }
}
