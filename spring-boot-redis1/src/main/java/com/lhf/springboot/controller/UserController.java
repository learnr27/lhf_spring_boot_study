package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import com.lhf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 16:32
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:9010/find?id=3
    @GetMapping("find")
    public Object find(Integer id){
        return userService.findUserById(id);
    }

    //http://localhost:9010/update?id=2&name=%E7%8E%8B%E4%BA%94
    @GetMapping("update")
    public Object update(Integer id, String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(getRandom(6));
        user.setPhone("138" + getRandom(8));
        user.setEmail(getRandom(10) + "@qq.com");
        return userService.update(user);

    }

    public static String getRandom(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    //http://localhost:9010/delete?id=1
    @GetMapping("delete")
    public Object delete(Integer id) {
        userService.deleteById(new User(id));
        return "移除缓存 key = " + id;
    }

    //http://localhost:9010/deleteAll
    @GetMapping("deleteAll")
    public Object deleteAll() {
        userService.deleteAll();
        return "移除所有缓存";
    }

}
