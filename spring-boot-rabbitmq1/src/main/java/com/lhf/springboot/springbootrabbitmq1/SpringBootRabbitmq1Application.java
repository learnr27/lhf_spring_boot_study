package com.lhf.springboot.springbootrabbitmq1;

import com.lhf.springboot.springbootrabbitmq1.client.RabbitMQClient;
import com.lhf.springboot.springbootrabbitmq1.util.RandomUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootApplication
public class SpringBootRabbitmq1Application {

    @Autowired
    RabbitMQClient rabbitMQClient;

    //创建消息队列rabbitMQ，用来存放消息
    @Bean
    public Queue mqQueue(){
        return new Queue("rabbitMQ");
    }

    //发送一条
    /*@PostConstruct
    public void init(){
        rabbitMQClient.send(RandomUtils.getRandomString());   //发送消息
    }*/

    //单线程发送10000条
    @PostConstruct
    public void init() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {
            rabbitMQClient.send(RandomUtils.getRandomString());   //发送消息
        }
        stopWatch.stop();
        System.out.println("发送消息耗时：" + stopWatch.getTotalTimeMillis());
    }

    //开了10 个线程，每个线程发送 10000 条消息。
    /*@PostConstruct   //被 @PostConstruct 修饰的方法会在构造函数之后执行。
    public void init(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int threads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);  //创建线程池

        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threads);

        for(int i = 0; i < threads; i++){
            executorService.execute(() -> {
                try{
                    start.await();
                    for(int j = 0; j < 10000; j++){
                        rabbitMQClient.send(RandomUtils.getRandomString());   //发送消息
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    end.countDown();
                }
            });
        }
        start.countDown();
        try{
            end.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();   //关闭线程池
        }

        stopWatch.stop();
        System.out.println("发送消息耗时：" + stopWatch.getTotalTimeMillis());
    }*/

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitmq1Application.class, args);
    }

}
