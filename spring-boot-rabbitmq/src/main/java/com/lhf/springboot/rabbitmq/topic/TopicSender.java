package com.lhf.springboot.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TopicSender
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 19:18
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "一个人的秋天，孤独，彷徨";
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", context);
    }

    public void send1() {
        String context = "十年生死两茫茫，不思量，自难忘，千里孤坟，无处话凄凉";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
    }

    public void send2() {
        String context = "将近酒，杯莫停";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }
}
