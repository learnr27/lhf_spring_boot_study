package com.lhf.springboot.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhf.springboot.mapper.UserMapper;
import com.lhf.springboot.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/10 11:59
 */
@Api("用户API接口")
@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //http://localhost:9005/one?id=1
    @ApiOperation(value = "根据id获取用户信息")
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    @ResponseBody
    public User selectOne(@RequestParam(value = "id", required = true) Long id){
        User user = userMapper.selectById(id);
        return user;
    }

    @ApiOperation("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String insertUser(@RequestBody User user){
        User u = new User();
        u.setId(user.getId());
        u.setName(user.getName());
        u.setAge(user.getAge());
        u.setEmail(user.getEmail());

        Integer num = userMapper.insert(u);
        if(num > 0){
            return "用户添加成功";
        }else{
            return "添加失败！";
        }
    }

    @ApiOperation("根据id删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(value = "id", required = true) Long id){
        Integer num = userMapper.deleteById(id);
        if(num > 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }

    @ApiOperation("根据id更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User editUser(@RequestParam(value = "id" , required = true) Long id, @RequestParam(value = "name" , required = true)String name,
                           @RequestParam(value = "age" , required = true)Integer age, @RequestParam(value = "email" , required = true)String email){
        User user = userMapper.selectById(id);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        userMapper.update(user, null);

        return user;
    }

    @ApiOperation("获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> userList(){
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @ApiOperation("用户分页")
    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    @ResponseBody
    public List<User> userPage() {
        System.out.println("----- baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>()
                .gt("age", 6));

        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        System.out.println("总页数----->" + userIPage.getPages());

        List<User> userList = userIPage.getRecords();

        return userList;
    }



}
