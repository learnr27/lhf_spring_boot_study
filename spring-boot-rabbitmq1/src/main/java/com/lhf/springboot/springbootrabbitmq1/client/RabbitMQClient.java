package com.lhf.springboot.springbootrabbitmq1.client;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RabbitMQClient
 * @Author: liuhefei
 * @Description: RabbitMQ客户端: 实现消息发送
 * @Date: 2020/3/10 18:23
 */
@Component
public class RabbitMQClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息
    public void send(String message){
        rabbitTemplate.convertAndSend("rabbitMQ", message); // rabbitMQ 这个是路由规则（routingKey），它的值表明将消息发送到指定的队列 rabbitMQ 中去
    }

}
