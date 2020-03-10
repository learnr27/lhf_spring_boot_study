package com.lhf.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: HelloController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/6 20:35
 */
@Controller
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM user");
        return list.get(0);
    }

}
