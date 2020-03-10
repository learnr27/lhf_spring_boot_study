package com.lhf.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: SchedulerTask
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 17:26
 */
@Component
public class SchedulerTask {

    private int count = 0;

    @Scheduled(cron = "*/6 * * * * ?")
    private void process(){
        Date date = new Date();
        System.out.println(date + "---" + (count++));
    }
}
