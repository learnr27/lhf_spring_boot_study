package com.lhf.springboot.controller;

import com.lhf.springboot.mapper.UserMapper;
import com.lhf.springboot.model.ResultMap;
import com.lhf.springboot.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: LoginController
 * @Author: liuhefei
 * @Description: 登录控制器
 * @Date: 2019/5/24 16:37
 */
@RestController
public class LoginController {

    private final UserMapper userMapper;
    private final ResultMap resultMap;

    @Autowired
    public LoginController(UserMapper userMapper, ResultMap resultMap) {
        this.userMapper = userMapper;
        this.resultMap = resultMap;
    }


    //http://localhost:8084/login?username=admin&password=123456
    @PostMapping("/login")
    public ResultMap login(@RequestParam("username")String username,  @RequestParam("password")String password){
        String realPassword = userMapper.getPassword(username);
        if(realPassword == null){
            return resultMap.fail().code(401).message("用户名错误");
        }else if(!realPassword.equals(password)){
            return resultMap.fail().code(401).message("密码错误");
        }else {
            return resultMap.success().code(200).message(JWTUtil.createToken(username));
        }
    }

    //http://localhost:8084/unauthorized/123456
    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return resultMap.success().code(401).message(message);
    }

}
