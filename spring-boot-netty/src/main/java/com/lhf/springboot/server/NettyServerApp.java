package com.lhf.springboot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName: NettyServerApp
 * @Author: liuhefei
 * @Description: Netty服务端入口启动程序
 * @Date: 2019/7/25 11:56
 */
@SpringBootApplication
@EnableAutoConfiguration
public class NettyServerApp {

    public static void main(String[] args) {
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext context = SpringApplication.run(NettyServerApp.class, args);
        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.run();
    }
}
