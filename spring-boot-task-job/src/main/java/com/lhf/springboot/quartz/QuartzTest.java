package com.lhf.springboot.quartz;

import com.lhf.springboot.util.RandomUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @ClassName: QuartzTest
 * @Author: liuhefei
 * @Description: Quartz定时任务
 * @Date: 2019/10/31 19:08
 */
public class QuartzTest extends QuartzJobBean {
    //执行定时任务
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz定时任务1： " + RandomUtils.getRandomString());
    }
}
