package com.lhf.springboot.controller;

import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @ClassName: UserRestController
 * @Author: liuhefei
 * @Description: 用户控制层
 * @Date: 2019/7/26 10:40
 */
@Api(value = "用户Api接口", tags = "用户", description = "用户Api")
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
    private UserService userService;

	@ApiOperation("添加用户")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
    public boolean insert(@RequestBody User user) {
    	System.out.println("开始新增...");
        return userService.insert(user);
    }

    @ApiOperation("更新用户信息")
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
    public boolean update(@RequestBody User user) {
    	System.out.println("开始更新...");
        return userService.update(user);
    }

    @ApiOperation("删除用户")
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody User user)  {
    	System.out.println("开始删除...");
        return userService.delete(user);
    }

    @ApiOperation("查询用户")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findByUser(User user) {
    	System.out.println("开始查询...");
        return userService.findByListEntity(user);
    }

}
