package com.lhf.springboot.controller;

import com.lhf.springboot.common.BaseResponse;
import com.lhf.springboot.common.dao.entity.User;
import com.lhf.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 18:01
 */
@Api("用户API接口")
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public BaseResponse<String> addUser(@RequestBody User user){
        int num = userService.insertUser(user);
        if(num > 0){
            return new BaseResponse<>(true, "添加成功", "user");
        }else {
            return new BaseResponse<>(false, "添加失败", "user");
        }
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser1")
    public BaseResponse<String> addUser1(@RequestBody User user){
        int num = userService.insertUser1(user);
        if(num > 0){
            return new BaseResponse<>(true, "添加成功", "user");
        }else {
            return new BaseResponse<>(false, "添加失败", "user");
        }
    }


    @ApiOperation("添加用户")
    @PostMapping("/updateUser")
    public BaseResponse<String> updateUser(@RequestBody User user){
        int num = userService.updateUser(user);
        if(num > 0){
            return new BaseResponse<>(true, "更新成功", "user");
        }else {
            return new BaseResponse<>(false, "更新失败", "user");
        }

    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/findUserById")
    public BaseResponse<User> findUserById(@RequestParam(value = "id", required = true) Integer id){
        User user = userService.findById(id);
        return new BaseResponse<User>(true, "查询成功", user);
    }


    @ApiOperation("根据id查询用户")
    @GetMapping("/findUserById1")
    public BaseResponse<User> findUserById1(@RequestParam(value = "id", required = true) Integer id){
        User user = userService.findById1(id);
        return new BaseResponse<User>(true, "查询成功", user);
    }


    @ApiOperation("根据id删除用户")
    @DeleteMapping("/findUserById1")
    public BaseResponse<String> deleteUser(@RequestParam(value = "id", required = true) Integer id){
        int num  = userService.deleteUser(id);
        if(num > 0){
            return new BaseResponse<>(true, "删除成功", null);
        }else {
            return new BaseResponse<>(false, "删除失败", null);
        }
    }



}
