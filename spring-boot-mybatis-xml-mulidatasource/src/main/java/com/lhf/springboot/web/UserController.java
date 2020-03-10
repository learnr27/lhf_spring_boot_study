package com.lhf.springboot.web;

import com.lhf.springboot.mapper.test1.User1Mapper;
import com.lhf.springboot.mapper.test2.User2Mapper;
import com.lhf.springboot.model.Users;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/26 19:20
 */
@Api("用户Api接口")
@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<Users> getUsers() {
        List<Users> users=user1Mapper.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public Users getUser(Long id) {
        Users user=user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(Users user) {
        user2Mapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(Users user) {
        user2Mapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }
}
