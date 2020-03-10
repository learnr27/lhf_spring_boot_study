package com.lhf.springboot.config;

import com.lhf.springboot.quartz.QuartzTest;
import com.lhf.springboot.quartz.QuartzTest1;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: QuartzConfig
 * @Author: liuhefei
 * @Description: Quartz定时任务配置类
 * @Date: 2019/10/31 19:06
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(QuartzTest.class).withIdentity("quartzTest").storeDurably().build();

    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity("quartzTestJob")
                .withSchedule(scheduleBuilder)
                .build();
    }

    /*****************************************************************************************************************/

    @Bean
    public JobDetail interviewPlansJobDetail(){
        return JobBuilder.newJob(QuartzTest1.class)//PrintTimeJob我们的业务类
                .withIdentity("quartzTest1")//可以给该JobDetail起一个id
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                .usingJobData("msg1", "quartzTest1")//关联键值对
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger interviewPlansJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/3 * * * ? ");   // 0 0 0/1 * * ?
        return TriggerBuilder.newTrigger()
                .forJob(interviewPlansJobDetail())//关联上述的JobDetail
                .withIdentity("quartzTestJob1")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
