package com.lhf.springboot.client;

import com.lhf.springboot.protobuf.UserInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: NettyClientFilter
 * @Author: liuhefei
 * @Description: Netty客户端 过滤器
 * @Date: 2019/7/25 11:22
 */
@Component
public class NettyClientFilter extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyClientHandler nettyClientHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline ph = socketChannel.pipeline();
        //解码和编码，应和服务端一致
        //入参说明：杜超时时间，写超时时间，所有类型的超时时间/时间格式
        ph.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));

        //传输的协议 protobuf
        ph.addLast(new ProtobufVarint32FrameDecoder());
        ph.addLast(new ProtobufDecoder(UserInfo.UserMsg.getDefaultInstance()));
        ph.addLast(new ProtobufVarint32LengthFieldPrepender());
        ph.addLast(new ProtobufEncoder());

        //业务逻辑实现类
        ph.addLast("nettyClientHandler", nettyClientHandler);
    }
}
