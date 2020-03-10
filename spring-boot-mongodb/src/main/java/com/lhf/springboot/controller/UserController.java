package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import com.lhf.springboot.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/30 9:52
 */
@Api(value = "用户Api接口", tags = "用户Api")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user){
        userRepository.saveUser(user);
    }

    @ApiOperation("根据用户名查询用户信息")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public User findUserByName(@RequestParam(value = "name", required = true) String name){
        User user = userRepository.findUserByName(name);
        return user;
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public long updateUser(@RequestBody User user){
        long num = userRepository.updateUser(user);
        return num;
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam(value = "id", required = true) Integer id){
        userRepository.deleteUserById(id);
    }

}
