package com.lhf.springboot.rabbitmq.object;

import com.lhf.springboot.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ObjectReceiver
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 18:27
 */
@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    @RabbitHandler
    public void process(Message message){
        System.out.println("Receiver object : " + message);
    }
}
