package com.lhf.springboot.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lhf.springboot.beans.Message;
import com.lhf.springboot.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: KafkaSender
 * @Author: liuhefei
 * @Description: 消息发送者(生产者）
 * @Date: 2019/10/12 15:17
 */
@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    /**
     * 发送消息
     */
    public void sendMessage(){
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(RandomUtil.getRandomString(10));
        message.setSendTime(new Date());
        log.info("+++++++++++++ message = {}", gson.toJson(message));
        kafkaTemplate.send("kafka-topic", gson.toJson(message));

    }
}
