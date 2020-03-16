package com.lhf.springboot.springbootrocketmq1.client;

import com.lhf.springboot.springbootrocketmq1.util.RandomUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;

/**
 * @ClassName: RocketMQClient
 * @Author: liuhefei
 * @Description:  RocketMQ客户端:生产者
 * @Date: 2020/3/11 17:29
 */
@Component
public class RocketMQClient {

    //生产者的组名
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    //nameServer地址
    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    @PostConstruct
    public void defaultMQProducer(){
        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //指定nameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);

        try{

            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();

            //创建一个消息实例，包含：topic，tag和消息体
            //如下：topic为"Message", tag为"push"
            String body = RandomUtils.getRandomString();
            Message message = new Message("Message", "push", body.getBytes(RemotingHelper.DEFAULT_CHARSET));

            StopWatch stop = new StopWatch();
            stop.start();

            for(int i = 0; i < 1000;i++){
                SendResult result = producer.send(message);
                System.out.println("发送响应：MsgId: " + result.getMsgId() + " , 发送状态： " + result.getSendStatus());
            }
            stop.stop();;
            System.out.println("发送10000条消息耗时：" + stop.getTotalTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }
    }
}
