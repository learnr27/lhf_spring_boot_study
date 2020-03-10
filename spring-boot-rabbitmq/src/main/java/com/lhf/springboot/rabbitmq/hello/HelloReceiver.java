package com.lhf.springboot.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: HelloReceiver
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:26
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receiver : " + hello);
    }
}
