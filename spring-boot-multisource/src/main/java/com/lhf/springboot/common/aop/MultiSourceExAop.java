package com.lhf.springboot.common.aop;

import com.lhf.springboot.common.annotion.DataSource;
import com.lhf.springboot.common.mutidatasource.DSEnum;
import com.lhf.springboot.common.mutidatasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName: MultiSourceExAop
 * @Author: liuhefei
 * @Description: 多数据源切换的aop
 * @Date: 2019/7/31 17:17
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "lhf", name = "muti-datasource-open", havingValue = "true")
public class MultiSourceExAop implements Ordered {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.lhf.springboot.common.annotion.DataSource)")
    private void cut(){

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if(!(signature instanceof MethodSignature)){
            throw new IllegalStateException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DataSource dataSource = currentMethod.getAnnotation(DataSource.class);
        if (dataSource != null) {
            DataSourceContextHolder.setDataSourceType(dataSource.name());
            log.debug("设置数据源为：" + dataSource.name());
        } else {
            DataSourceContextHolder.setDataSourceType(DSEnum.DATA_SOURCE_CORE);
            log.debug("设置数据源为：dataSourceCore");
        }
        try {
            return point.proceed();
        } finally {
            log.debug("清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * aop的顺序要早于spring的事务
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
