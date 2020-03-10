package com.lhf.springboot.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TopicReceiver2
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 19:16
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("Topic Receiver2  : " + message);
    }
}
