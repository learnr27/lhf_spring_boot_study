package com.lhf.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: DashboardController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:24
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping("/")
    public String index(){
        return "view/pages/index.html";
    }

}
