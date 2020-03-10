package com.lhf.springboot.controller;

import com.lhf.springboot.pojo.ApiResult;
import com.lhf.springboot.pojo.CurrentUser;
import com.lhf.springboot.pojo.UserInfo;
import com.lhf.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 12:38
 */
@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("login")
    public ApiResult login(@RequestBody UserInfo userInfo){
        return new ApiResult(200, "登录成功", userInfoService.buildUserInfo(userInfo));
    }

    @GetMapping("user/info")
    public ApiResult info(){
        return new ApiResult(200, null, CurrentUser.get());
    }

    @PostMapping("logout")
    public ApiResult logout(@RequestHeader("Authorization") String jwt){
        userInfoService.logout(jwt);
        return new ApiResult(200, "成功", null);
    }

}
