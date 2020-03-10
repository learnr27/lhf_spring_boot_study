package com.lhf.springboot.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StrReceiver2
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:32
 */
@Component
@RabbitListener(queues = "neo")
public class StrReceiver2 {

    @RabbitHandler
    public void process(String str) {
        System.out.println("Receiver 2: " + str);
    }

}
