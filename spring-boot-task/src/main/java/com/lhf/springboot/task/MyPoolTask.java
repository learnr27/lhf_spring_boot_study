package com.lhf.springboot.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: MyPoolTask
 * @Author: liuhefei
 * @Description: 自定义线程池
 * @Date: 2019/7/29 15:57
 */
@Configuration
public class MyPoolTask {

    @Bean("MyExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数，最大线程数，任务队列数
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("taskExecutor - ");
        //设置线程池关闭的时候等待所有任务都完成再继续销毁其他的bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 指定上述线程池
     * @return
     */
    @Async("MyExecutor")
    public Future<String> test1(){
        System.out.println("-----开始-----");
        try{
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("-----结束------");
        return new AsyncResult<>("任务执行完成");

    }
}
