package com.lhf.springboot.springTask;

import com.lhf.springboot.util.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @ClassName: SpringTaskDemo
 * @Author: liuhefei
 * @Description: Spring Task实现定时任务
 * @Date: 2019/10/31 18:31
 */
@Component
public class SpringTaskDemo {

    private final Logger logger = LoggerFactory.getLogger(SpringTaskDemo.class);

    @Scheduled(cron = "0/5 * * * * * ")
    public void scheduledCron(){
        logger.info("===>>使用cron表达式实现定时任务：" + System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 6000)
    public void scheduledFixedRate(){
        logger.info("===>>使用fixedRate实现定时任务： " + RandomUtils.getRandomString(8));
    }

    @Scheduled(fixedDelay = 7000)
    public void scheduledFixedDelay(){
        logger.info("===>>使用fixedDelay实现定时任务： " + new Date() + " -- " +  RandomUtils.getRandomString(4));
    }


}
