package com.lhf.springboot.echarts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 15:44
 */
@Controller
public class IndexController {
    /**
     * 首页
     */
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
