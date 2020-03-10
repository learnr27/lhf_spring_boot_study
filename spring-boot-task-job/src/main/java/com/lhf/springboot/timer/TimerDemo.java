package com.lhf.springboot.timer;

import com.lhf.springboot.util.RandomUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: TimerDemo
 * @Author: liuhefei
 * @Description: Timer定时任务
 * @Date: 2019/10/31 17:58
 */
public class TimerDemo {

   public void timerJob(){
       TimerTask timerTask = new TimerTask() {
           @Override
           public void run() {
               System.out.println("Timer Job: " + RandomUtils.getRandomString());
           }
       };
       Timer timer = new Timer();
       //每3秒执行一次
       timer.schedule(timerTask, 10, 3000);
   }
}
