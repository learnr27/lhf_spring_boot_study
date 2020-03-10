package com.lhf.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/31 17:07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable(value = "username")String username, @PathVariable(value = "password")String password){
        /*String username = request.getParameter("username");
        String password = request.getParameter("password");*/
        System.out.println(username + " : " + password);
        if("admin".equals(username) && "654321".equals(password)){
            System.out.println("登陆成功！");
            return "success";
        }
        return "fail";

    }

}
