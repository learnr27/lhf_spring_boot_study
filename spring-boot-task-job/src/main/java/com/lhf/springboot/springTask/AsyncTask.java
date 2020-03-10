package com.lhf.springboot.springTask;

import com.lhf.springboot.util.RandomUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AsyncTask
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/31 18:57
 */
@Async  //开启异步任务类,类中的每个方法都是定时任务，该注解可以加到类和方法上
@Component
public class AsyncTask {

    //@Async
    @Scheduled(cron = "0/7 * * * * * ")
    public void task1(){
        System.out.println("异步任务1： " + System.currentTimeMillis());
    }

    //@Async
    @Scheduled(cron = "0/9 * * * * * ")
    public void task2(){
        System.out.println("异步任务2：" + RandomUtils.getRandomString());
    }


}
