package com.lhf.springboot.model;

import lombok.Data;

/**
 * @ClassName: LoginParam
 * @Author: liuhefei
 * @Description: 登录认证接口参数
 * @Date: 2019/7/31 14:44
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * Application ID
     */
    private String appid;
    /**
     * IMEI码
     */
    private String imei;
}
