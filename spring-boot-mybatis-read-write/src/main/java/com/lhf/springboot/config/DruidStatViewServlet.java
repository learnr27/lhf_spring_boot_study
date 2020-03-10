package com.lhf.springboot.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @ClassName: DruidStatViewServlet
 * @Author: liuhefei
 * @Description: Druid的Servlet
 * @Date: 2019/8/28 12:16
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*",
initParams = {
        //@WebInitParam(name="allow",value="127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name="loginUsername",value="admin"),// 用户名
        @WebInitParam(name="loginPassword",value="123456"),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {
}
