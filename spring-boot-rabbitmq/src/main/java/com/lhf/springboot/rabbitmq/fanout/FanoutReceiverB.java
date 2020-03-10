package com.lhf.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FanoutReceiverB
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:20
 */
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String message){
        System.out.println("fanout Receiver B: " + message);
    }
}
