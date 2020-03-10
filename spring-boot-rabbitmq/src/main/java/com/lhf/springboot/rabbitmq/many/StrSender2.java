package com.lhf.springboot.rabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StrSender2
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:32
 */
@Component
public class StrSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "spirng boot str queue"+" ****** "+i;
        System.out.println("Sender2 : " + context);
        this.rabbitTemplate.convertAndSend("str", context);
    }

}
