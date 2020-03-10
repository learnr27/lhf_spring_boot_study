package com.lhf.springboot.quartz;

import com.lhf.springboot.util.RandomUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ClassName: QuartzTest1
 * @Author: liuhefei
 * @Description: Quartz定时任务
 * @Date: 2019/10/31 19:17
 */
public class QuartzTest1 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz定时任务2: " + RandomUtils.getRandomNum(4) + "-->" + RandomUtils.getRandomString());
    }
}
