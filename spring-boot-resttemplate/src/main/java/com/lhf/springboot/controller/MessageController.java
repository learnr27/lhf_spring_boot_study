package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: MessageController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 16:04
 */
@RestController
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @GetMapping(value = "/msg")
    public String msg(@RequestParam(value = "message", required = false, defaultValue = "越长大越孤单")String message){
        logger.info("消息：{}", message);
        return message;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        logger.info("用户数据：{}", user);
        return user;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam(value = "id", required = true) Integer id,
                        @RequestParam(value = "name", required = true)String name,
                        @RequestParam(value = "password", required = true)String password,
                        @RequestParam(value = "phone", required = true)String phone){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        logger.info("用户数据：{}", user);
        return user;
    }


}
