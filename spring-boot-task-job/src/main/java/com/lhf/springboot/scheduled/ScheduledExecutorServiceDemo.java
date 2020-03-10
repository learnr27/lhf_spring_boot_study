package com.lhf.springboot.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Date;

/**
 * @ClassName: ScheduledExecutorServiceDemo
 * @Author: liuhefei
 * @Description: ScheduledExecutorService实现定时任务
 * @Date: 2019/10/31 18:22
 */
public class ScheduledExecutorServiceDemo {

    public void scheduledJob(){

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        //参数：1.任务名称 2. 首次执行的延时时间 3. 任务执行间隔 4. 间隔时间单位
        service.scheduleAtFixedRate(()->System.out.println("ScheduledExecutorService Job : " + new Date()), 0, 4, TimeUnit.SECONDS);

    }
}
