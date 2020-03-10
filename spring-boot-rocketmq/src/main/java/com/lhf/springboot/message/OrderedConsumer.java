package com.lhf.springboot.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: OrderedConsumer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/24 17:30
 */
public class OrderedConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderedConsumerGroup");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("OrderedConsumer");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);  //从最早的消息开始消费
        consumer.subscribe("TopicTest11", "TagA || TagC || TagE");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            AtomicLong consumeTimes = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for(MessageExt messageExt : msgs){
                    System.out.println("rcv msg " + new String(messageExt.getBody()));
                }

                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + " %n");
                this.consumeTimes.incrementAndGet();
                if((this.consumeTimes.get() % 2) == 0){
                    return ConsumeOrderlyStatus.SUCCESS;
                }else if((this.consumeTimes.get() % 3) == 0){
                    // return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.%n");
    }
}
