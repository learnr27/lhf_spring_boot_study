package com.lhf.springboot.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FanoutReceiverC
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:20
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {

    @RabbitHandler
    public void process(String message) {
        System.out.println("fanout Receiver C: " + message);
    }

}
