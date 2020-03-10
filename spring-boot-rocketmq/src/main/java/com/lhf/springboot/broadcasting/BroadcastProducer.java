package com.lhf.springboot.broadcasting;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: BroadcastProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 11:32
 */
public class BroadcastProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("BroadcastProducerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("BroadcastProducer");
        producer.start();

        for(int i=0;i<10;i++){
            Message msg = new Message("TopicBroadcast",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
