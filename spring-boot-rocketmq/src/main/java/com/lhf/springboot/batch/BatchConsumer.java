package com.lhf.springboot.batch;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName: BatchConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 11:10
 */
public class BatchConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("BatchConsumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("BatchConsumer");
        //订阅指定BatchTopic下tags分别等于TagA或TagC或TagD
        consumer.subscribe("BatchTopic", "TagA || TagC || TagD");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());
                for(MessageExt msg : msgs){
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // Consumer对象在使用之前必须要调用start初始化，初始化一次即可
        consumer.start();
        System.out.println("ConsumerStarted...");
    }
}
