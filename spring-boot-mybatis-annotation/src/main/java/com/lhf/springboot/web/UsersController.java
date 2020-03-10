package com.lhf.springboot.web;

import com.lhf.springboot.mapper.UsersMapper;
import com.lhf.springboot.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UsersController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/3 17:52
 */
@Api("用户API接口文档")
@RestController
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    //http://localhost:8095/getUsers
    @ApiOperation("获取所有用户")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<Users> getUsers() {
        List<Users> users=usersMapper.getAll();
        return users;
    }

    //http://localhost:8095/getUser?id=1
    @ApiOperation("根据id获取用户信息")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Users getUser(Long id) {
        Users users=usersMapper.getOne(id);
        return users;
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void save(@RequestBody  Users users) {
        usersMapper.insert(users);
    }

    @ApiOperation("更新用户信息")
    @RequestMapping(value="update", method = RequestMethod.POST)
    public void update(@RequestBody Users users) {
        usersMapper.update(users);
    }

    @ApiOperation("根据id删除用户信息")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        usersMapper.delete(id);
    }
}
