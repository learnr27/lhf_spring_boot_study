package com.lhf.springboot.simple;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName: ThreeWayConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/25 15:49
 */
public class ThreeWayConsumer {
    /**
     * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。
     * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法
     */
    public static void main(String[] args) throws MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        //实例化消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ThreeWayConsumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("Consumber");

        //订阅指定topic下tags分别等于TagA或TagB或TagC或TagD
        consumer.subscribe("TopicTest1", "TagA || TagB || TagC || TagD");
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic
         */
        consumer.subscribe("TopicTest2", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + "Receive New Message: " + msgs.size());
                MessageExt msg = msgs.get(0);
                if(msg.getTopic().equals("TopicTest1")){
                    //执行TopicTest1的消费逻辑
                    if (msg.getTags() != null && "TagA".equals(msg.getTags())) {
                        //执行TagA的消费
                        System.out.println("A---------" + new String(msg.getBody()));
                    } else if (msg.getTags() != null
                            && msg.getTags().equals("TagB")) {
                        //执行TagC的消费
                        System.out.println("B---------" + new String(msg.getBody()));
                    } else if (msg.getTags() != null
                            && msg.getTags().equals("TagC")) {
                        //执行TagC的消费
                        System.out.println("C---------" + new String(msg.getBody()));
                    } else if (msg.getTags() != null
                            && msg.getTags().equals("TagD")) {
                        //执行TagD的消费
                        System.out.println("D---------" + new String(msg.getBody()));
                    }
                }else if(msg.getTopic().equals("TopicTest2")){
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //Consumer对象在使用之前必须要调用start初始化，初始化一次即可
        consumer.start();
        System.out.println("ConsumerStarted。。。。");
    }
}
