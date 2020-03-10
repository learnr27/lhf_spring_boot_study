package com.lhf.springboot.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: HelloSender
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:27
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "Hello " + new Date();
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("hello ", context);
    }
}
