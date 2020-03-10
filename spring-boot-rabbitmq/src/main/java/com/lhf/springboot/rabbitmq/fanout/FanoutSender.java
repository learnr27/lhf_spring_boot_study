package com.lhf.springboot.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: FanoutSender
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:20
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "hi, 欢迎来到这里！";
        System.out.println("sender: " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
    }
}
