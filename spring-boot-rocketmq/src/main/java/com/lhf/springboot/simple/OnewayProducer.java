package com.lhf.springboot.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;


/**
 * @ClassName: OnewayProducer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/25 15:48
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        //实例化生产者组
        DefaultMQProducer producer = new DefaultMQProducer("OnewayProducerGroup1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setInstanceName("OnewayProducer");
        //启动实例
        producer.start();
        for(int i = 0;i < 100; i++){
            //创建消息实例，指定主题、标签和消息正文。
            Message msg = new Message("TopicTest2", //主题
                    "TagA",   //标签
                    ("OnewayProducer Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)  //消息内容
            );
            producer.sendOneway(msg);  //发送消息
        }
        producer.shutdown();  //关闭生产者实例
    }
}
