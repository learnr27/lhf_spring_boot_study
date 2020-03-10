package com.lhf.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: IndexController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 16:48
 */
@Controller
public class IndexController {

    @GetMapping(name = "/hello")
    public String hello(Model model, ModelMap modelMap) {
        modelMap.addAttribute("name", "freemarker2");
//        model.addAttribute("name", "freemarker");
        return "index";
    }
}
