package com.lhf.springboot.rabbitmq.object;

import com.lhf.springboot.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ObjectSender
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 19:12
 */
@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Message message){
        System.out.println("Sender object: " + message.toString());
        this.rabbitTemplate.convertAndSend("object", message);
    }
}
