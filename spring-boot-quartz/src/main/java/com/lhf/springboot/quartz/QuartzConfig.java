package com.lhf.springboot.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: QuartzConfig
 * @Author: liuhefei
 * @Description: Quartz配置类
 * @Date: 2019/7/3 15:36
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail testQuartz1(){
        return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1(){
        //5秒执行一次
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("testTask1")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail testQuartz2(){
        return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2(){
        //cron方式，每隔6秒执行一次
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("testTask2")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/6 * * * * ?"))
                .build();
    }
}
