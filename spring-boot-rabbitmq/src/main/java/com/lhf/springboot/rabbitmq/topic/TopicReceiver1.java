package com.lhf.springboot.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TopicReceiver1
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 19:13
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String message){
        System.out.println("Topic Receiver1  : " + message);
    }
}
