package com.lhf.springboot.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: NettyServer
 * @Author: liuhefei
 * @Description: Netty服务端
 * @Date: 2019/7/25 11:51
 */
@Service("nettyServer")
public class NettyServer {
    private static final int port = 9876; // 设置服务端端口
    private static EventLoopGroup boss = new NioEventLoopGroup(); // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup work = new NioEventLoopGroup(); // 通过nio方式来接收连接和处理连接
    private static ServerBootstrap boot = new ServerBootstrap();

    @Autowired
    private NettyServerFilter nettyServerFilter;

    public void run() {
        try {
            boot.group(boss, work);
            boot.channel(NioServerSocketChannel.class);
            boot.childHandler(nettyServerFilter); // 设置过滤器
            // 服务器绑定端口监听
            ChannelFuture f = boot.bind(port).sync();
            System.out.println("服务端启动成功,端口是:" + port);
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}
