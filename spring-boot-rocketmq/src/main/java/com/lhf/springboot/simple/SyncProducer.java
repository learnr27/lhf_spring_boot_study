package com.lhf.springboot.simple;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: SyncProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/25 15:49
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //实例化生产者组
        DefaultMQProducer producer = new DefaultMQProducer("syncProducerGroup1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("SyncProducer");
        //启动实例
        producer.start();
        for(int i = 0;i < 100;i++){
            //创建消息，指定消息主题，标签，内容
            Message message = new Message("TopicTest1" , //主题
                    "TagB",  //标签
                    ("SyncProducer Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)  //内容
                    );
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();  //关闭
    }
}
