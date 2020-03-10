package com.lhf.springboot.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDate;

/**
 * @ClassName: TestTask2
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/3 15:44
 */
public class TestTask2 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("TestQuartz02----" + LocalDate.now());
    }
}
