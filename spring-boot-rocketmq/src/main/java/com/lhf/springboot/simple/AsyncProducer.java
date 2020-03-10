package com.lhf.springboot.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName: AsyncProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/25 15:49
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException {
        //实例化生产者组
        final DefaultMQProducer producer = new DefaultMQProducer("AsyncProducerGroup1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("AsyncProducer");
        //启动实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for(int i = 0; i < 100;i++){
            final int index = i;
            //创建消息，指定消息主题，标签，内容
            Message message = new Message("TopicTest1",   //主题
                    "TagC",  //标签
                    "OrderID188",  //keys
                    ("AsyncProducer Hello world " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)  //内容
               );
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.printf("%-10d Exception %s %n", index, throwable);
                    throwable.printStackTrace();
                }
            });
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                System.out.println("AsyncProducer shut down");
            }
        }));
    }
}
