package com.lhf.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @ClassName: SimpleScheduleConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/11/4 17:55
 */
@Configuration
@EnableScheduling  //开启定时任务
public class SimpleScheduleConfig {

    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行定时任务1: " + LocalDateTime.now());
    }
}
