package com.lhf.springboot.web;

import com.lhf.springboot.model.User;
import com.lhf.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 14:51
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getUser")
    public User getUser(){
        User user = userRepository.findByUserName("aa");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        System.out.println(user);
        return user;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users=userRepository.findAll();
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        System.out.println(users);
        return users;
    }

    @RequestMapping("/user/{id}")
    public User findById(Integer id){
        User user = userRepository.findById(id);
        return user;
    }
}
