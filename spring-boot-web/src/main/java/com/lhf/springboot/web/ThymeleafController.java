package com.lhf.springboot.web;

import javafx.scene.input.DataFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Date;

/**
 * @ClassName: ThymeleafController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 14:51
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/hi")
    public String thymeleaf(Locale locale, Model model){
        model.addAttribute("greeting", "你好！");

        Date date = new Date();
        DateFormat dataFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dataFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "hello";

    }
}
