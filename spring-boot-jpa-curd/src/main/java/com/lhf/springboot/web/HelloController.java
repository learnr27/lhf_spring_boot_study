package com.lhf.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: HelloController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 17:05
 */
@Controller
public class HelloController {


    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "霜花似雪") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}
