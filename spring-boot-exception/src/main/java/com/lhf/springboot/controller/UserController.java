package com.lhf.springboot.controller;

import com.lhf.springboot.exception.BizException;
import com.lhf.springboot.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/26 16:46
 */
@Api("用户api接口")
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @ApiOperation("添加用户")
    @PostMapping("/user")
    public boolean insert(@RequestBody User user) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        if(user.getName()==null){
            throw  new BizException("-1","用户姓名不能为空！");
        }
        return true;
    }

    @ApiOperation("更新用户")
    @PutMapping("/user")
    public boolean update(@RequestBody User user) {
        System.out.println("开始更新...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str=null;
        str.equals("111");
        return true;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/user")
    public boolean delete(@RequestBody User user)  {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @ApiOperation("查询用户")
    @GetMapping("/user")
    public List<User> findByUser(User user) {
        System.out.println("开始查询...");
        List<User> userList =new ArrayList<>();
        User user2=new User();
        user2.setId(1L);
        user2.setName("小三");
        user2.setAge(18);
        userList.add(user2);
        return userList;
    }
}
