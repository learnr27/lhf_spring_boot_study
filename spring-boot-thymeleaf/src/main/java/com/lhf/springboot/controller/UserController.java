package com.lhf.springboot.controller;

import com.lhf.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/27 16:35
 */
@Controller
public class UserController {
    @Autowired
    User user;

    @GetMapping("/users")
    public ModelAndView userList(){
        ModelAndView mv = new ModelAndView();
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setName("张三");
        user1.setAge(20);
        user1.setSex("男");
        user1.setHeight(170);
        user1.setWeight(67);
        user1.setPhone("18296615502");
        user1.setEmail("265465875@qq.com");
        user1.setAddress("广东深圳");
        user1.setHobby("游戏，电影，泡妞");
        user1.setMotto("吃喝玩乐");

        User user2 = new User();
        user2.setName("若梦");
        user2.setAge(18);
        user2.setSex("女");
        user2.setHeight(168);
        user2.setWeight(50);
        user2.setPhone("13567894421");
        user2.setEmail("djkdjh@163.com");
        user2.setAddress("云南昆明");
        user2.setHobby("绘画，旅行，逛吃");
        user2.setMotto("一个人很自由");

        users.add(user1);
        users.add(user2);

        mv.addObject("users", users);
        mv.setViewName("users");
        System.out.println(users);

        return mv;
    }

    @GetMapping("/user")
    @ResponseBody
    public User getUser(){
        return user;
    }
}
