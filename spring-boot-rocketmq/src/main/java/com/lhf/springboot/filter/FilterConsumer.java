package com.lhf.springboot.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName: FilterConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 11:58
 */
public class FilterConsumer {

    public static void main(String[] args) throws MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("FilterConsumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("FilterConsumer");

        //订阅消息
        consumer.subscribe("FilterTopic", MessageSelector.bySql(" a between 0 and 3 "));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName()
                        + " Receive New Messages: " + msgs.size());
                for(MessageExt msg : msgs){
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可
         */
        consumer.start();
        System.out.println("ConsumerStarted.");
    }
}
