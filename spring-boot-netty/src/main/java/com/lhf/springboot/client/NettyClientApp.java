package com.lhf.springboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName: NettyClientApp
 * @Author: liuhefei
 * @Description: Netty 客户端启动程序
 * @Date: 2019/7/25 11:29
 */
@SpringBootApplication
public class NettyClientApp {

    public static void main(String[] args) {
        //启动嵌入式Tomcat ,并初始化Spring环境及其各Spring组件
        ApplicationContext context = SpringApplication.run(NettyClientApp.class, args);
        NettyClient nettyClient = context.getBean(NettyClient.class);
        nettyClient.run();
    }
}
