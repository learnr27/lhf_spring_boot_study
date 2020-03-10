package com.lhf.springboot.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StrReceiver1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:31
 */
@Component
@RabbitListener(queues = "str")
public class StrReceiver1 {

    @RabbitHandler
    public void process(String str){
        System.out.println("Receiver 1: " + str);
    }
}
