package com.lhf.springboot.controller;

import com.lhf.springboot.pojo.User;
import com.lhf.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 20:17
 */
@Api(value = "用户ES Api接口", tags = "用户")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public boolean createUser(@RequestBody User user) {
        return userService.insert(user);
    }


    @ApiOperation("根据关键字进行全文搜索用户")
    @GetMapping("/user/searchContent")
    public List<User> search(@RequestParam(value = "searchContent") String searchContent) {
        return userService.search(searchContent);
    }

    @ApiOperation("根据关键字进行搜索用户并分页")
    @GetMapping("/searchUser")
    public List<User> searchUser(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUser(pageNumber, pageSize, searchContent);
    }


    @ApiOperation("根据关键词权重进行查询用户")
    @GetMapping("/userWeight")
    public List<User> searchUserByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUserByWeight(searchContent);
    }

    @ApiOperation("根据用户名进行搜索用户并分页")
    @GetMapping("/searchByName")
    public List<User> searchByName(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "name") String name) {
        return userService.searchByName(pageNumber, pageSize, name);
    }

    @ApiOperation("根据用户住址进行全文搜索用户")
    @GetMapping("/user/address")
    public List<User> findUserByAddressEquals(@RequestParam(value = "address") String address) {
        return userService.findUserByAddressEquals(address);
    }

    @ApiOperation("根据用户手机号查询并进行分页")
    @GetMapping("/user/phone")
    public Page<User> findUserByPhone(@RequestParam(value = "phone") String phone, @RequestParam(value = "pageable")Pageable pageable){
        return userService.findUserByPhone(phone, pageable);
    }

    @ApiOperation("根据用户名或手机号查询用户")
    @GetMapping("/user/np")
    public List<User> findUserByNameOrPhone(@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone){
        return userService.findUserByNameOrPhone(name, phone);
    }

}
