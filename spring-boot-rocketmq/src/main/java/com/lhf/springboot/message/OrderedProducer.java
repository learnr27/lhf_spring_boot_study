package com.lhf.springboot.message;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName: OrderedProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 17:30
 */
public class OrderedProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("OrderedProducerGroup");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("OrderedProducer");
        producer.setRetryTimesWhenSendAsyncFailed(3);   // 消息发送失败重试次数，重试3次
        producer.start();
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TadD", "TagE"};
        for(int i = 0;i < 10;i++ ){
            int orderId = i % 2;
            Message msg = new Message("TopicTest11", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ : " + i +", orderId : " + orderId).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer)arg;
                    int index = id % mqs.size();

                    return mqs.get(index);
                }
            }, orderId);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
