package com.lhf.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @ClassName: AsyncConfig
 * @Author: liuhefei
 * @Description: 定义一个支持异步任务的配置类
 * @Date: 2019/10/31 18:53
 */
@Configuration   //表明该类是一个配置类
@EnableAsync //开启异步事件的支持
public class AsyncConfig {

    /*
     *此处成员变量应该使用@Value从配置中读取
     */
    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
