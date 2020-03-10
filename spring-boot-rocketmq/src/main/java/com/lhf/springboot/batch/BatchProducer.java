package com.lhf.springboot.batch;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BatchProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 11:11
 */
public class BatchProducer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("BatchProducerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("BatchProducer");
        producer.start();

        String topic = "BatchTopic";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagC", "OrderID003", "Hello world 2".getBytes()));
        try {
            producer.send(messages);
        } catch (Exception e) {
            e.printStackTrace();
            //handle the error
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();

    }
}
