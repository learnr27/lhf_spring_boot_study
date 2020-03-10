package com.lhf.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @ClassName: HomeController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/28 14:27
 */
@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public String index(Model model){
        logger.info("----------打印日志-----------");
        return "index";
    }
}
