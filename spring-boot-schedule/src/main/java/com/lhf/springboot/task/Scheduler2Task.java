package com.lhf.springboot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Scheduler2Task
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/30 17:34
 */
@Component
public class Scheduler2Task {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedDelay = 6000)
    public void reportCurrentTime(){
        System.out.println("现在时间： " + dateFormat.format(new Date()));
    }
}
