package com.lhf.springboot.springbootrabbitmq1.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RabbitMQServer
 * @Author: liuhefei
 * @Description: RabbitMQ服务端：实现消息接收
 * @Date: 2020/3/10 18:24
 */
@Component
public class RabbitMQServer {

    //监听消息队列，并从队列中获取消息
    @RabbitListener(queues = "rabbitMQ")    //监听队列
    public void receive(String message){
        System.out.println("收到的 message 是：" + message);
    }
}
