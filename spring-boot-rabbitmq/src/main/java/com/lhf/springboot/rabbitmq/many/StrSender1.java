package com.lhf.springboot.rabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: NeoSender1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:33
 */
@Component
public class StrSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i){
        String context = "Spring boot str queue " + " ***** " + i;
        System.out.println("Sender1 : " + context);
        this.rabbitTemplate.convertAndSend("str", context);
    }
}
