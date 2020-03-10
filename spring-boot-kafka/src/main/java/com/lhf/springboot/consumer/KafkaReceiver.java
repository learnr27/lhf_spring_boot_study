package com.lhf.springboot.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName: KafkaReceiver
 * @Author: liuhefei
 * @Description: 消息接受者(消费者）
 * @Date: 2019/10/12 15:13
 */
@Component
@Slf4j
public class KafkaReceiver {
    /**
     * 客户端 consumer 接收消息特别简单，直接用 @KafkaListener 注解即可，并在监听中设置监听的 topic ， topics 是一个数组所以是可以绑定多个主题的，上面的代码中修改为 @KafkaListener(topics={"zhisheng","tian"}) 就可以同时监听两个 topic 的消息了。需要注意的是：这里的 topic 需要和消息发送类 KafkaSender.java 中设置的 topic 一致。
     * @param record
     */
    @KafkaListener(topics = {"kafka-topic"})
    public void listener(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            log.info("record = {}", record);
            log.info("message = {}", message);
        }
    }
}
