package com.lhf.springboot.web;

import com.lhf.springboot.mapper.test1.User1Mapper;
import com.lhf.springboot.mapper.test2.User2Mapper;
import com.lhf.springboot.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UsersController
 * @Author: liuhefei
 * @Description: 操作多数据源
 * @Date: 2019/6/4 11:57
 */
@Api("用户API接口")
@RestController
public class UsersController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @ApiOperation("获取所有用户信息")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<Users> getUsers() {
        List<Users> users=user1Mapper.getAll();
        return users;
    }

    @ApiOperation("根据用户id获取用户信息")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Users getUser(Long id) {
        Users users=user2Mapper.getOne(id);
        return users;
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void save(@RequestBody Users users) {
        user2Mapper.insert(users);
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value="update", method = RequestMethod.POST)
    public void update(Users users) {
        user2Mapper.update(users);
    }

    @ApiOperation("根据id删除用户信息")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }
}
