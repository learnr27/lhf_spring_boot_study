package com.lhf.springboot.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @ClassName: HelloController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 14:50
 *
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Locale locale, Model model){
        return "不思量，自难忘";
    }
}
