package com.lhf.springboot.controller;

import com.lhf.springboot.model.UserInfo;
import com.lhf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/11 16:16
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserInfo> findUserById(@PathVariable(value = "id", required = true) Long id){

        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }
}
