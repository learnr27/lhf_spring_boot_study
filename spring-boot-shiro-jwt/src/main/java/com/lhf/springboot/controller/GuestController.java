package com.lhf.springboot.controller;

import com.lhf.springboot.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GuestController
 * @Author: liuhefei
 * @Description: 游客角色可以访问的页面
 * @Date: 2019/5/24 16:36
 */
@RestController
@RequestMapping("/guest")
public class GuestController {
    private final ResultMap resultMap;

    @Autowired
    public GuestController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    //http://localhost:8084/guest/welcome
    @GetMapping("/welcome")
    public ResultMap login() {
        return resultMap.success().code(200).message("欢迎访问游客页面！");
    }
}
