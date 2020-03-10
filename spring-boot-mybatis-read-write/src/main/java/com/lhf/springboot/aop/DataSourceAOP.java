package com.lhf.springboot.aop;

import com.lhf.springboot.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceAOP
 * @Author: liuhefei
 * @Description: 数据源的切入面
 * @Date: 2019/8/28 11:02
 */
@Aspect
@Component
@Slf4j
public class DataSourceAOP {

    @Before("(@annotation(com.lhf.springboot.annotation.Master) || execution(* com.lhf.springboot.service..*.insert(..)) || " +
            "execution(* com.lhf.springboot.service..*.update*(..)) || execution(* com.lhf.springboot.service..*.delete*(..)) ||" +
            "execution(* com.lhf.springboot.service..*.add*(..))) && !@annotation(com.lhf.springboot.annotation.Slave) -"
    )
    public void setWriteDataSourceType(){
        DynamicDataSource.master();
        log.info("dataSource切换到：master");
    }

    @Before("(@annotation(com.lhf.springboot.annotation.Slave) || execution(* com.lhf.springboot.service..*.select*(..)) || " +
            "execution(* com.lhf.springboot.service..*get*(..))) && !@annotation(com.lhf.springboot.annotation.Master)")
    public void setReadDataSourceType(){
        DynamicDataSource.slave();
        log.info("dataSource切换到：slave");
    }
}
