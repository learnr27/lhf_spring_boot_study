package com.lhf.springboot;

import com.lhf.springboot.scheduled.ScheduledExecutorServiceDemo;
import com.lhf.springboot.springTask.SpringTaskDemo;
import com.lhf.springboot.timer.TimerDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   //注解开启对定时任务的支持
public class SpringBootTaskJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskJobApplication.class, args);

        TimerDemo timerDemo = new TimerDemo();
        timerDemo.timerJob();

        ScheduledExecutorServiceDemo sch = new ScheduledExecutorServiceDemo();
        sch.scheduledJob();

        SpringTaskDemo st = new SpringTaskDemo();
        st.scheduledCron();
        st.scheduledFixedRate();
        st.scheduledFixedDelay();


    }

}
