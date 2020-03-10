package com.lhf.springboot.controller;

import com.lhf.springboot.model.BaseResponse;
import com.lhf.springboot.model.LoginParam;
import com.lhf.springboot.model.UnbindParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 15:25
 */
@Api("登录API接口")
@RestController
public class LoginController {

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<String> login(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType,
                                      @RequestBody LoginParam loginParam) {
        _logger.info("用户请求登录获取Token");
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        return new BaseResponse<>(true, "Login success", username + password);
    }

    @ApiOperation("解绑")
    @PostMapping("/unbind")
    public BaseResponse<String> unbind(@RequestHeader(name = "Content-Type", defaultValue = "application/json") String contentType,
                                       @RequestHeader(name = "Authorization", defaultValue = "token") String token,
                                       @RequestBody UnbindParam unbindParam) {
        _logger.info("解绑通知接口start");
        String imei = unbindParam.getImei();
        String location = unbindParam.getLocation();
        return new BaseResponse<>(true, "解绑通知发送成功", "unbind");
    }
}
