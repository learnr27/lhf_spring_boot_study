package com.lhf.springboot.aop;

import com.lhf.springboot.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceAop
 * @Author: liuhefei
 * @Description: 设置路由key   默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 * @Date: 2019/8/28 16:53
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.lhf.springboot.annotation.Master) " +
            "&& (execution(* com.lhf.springboot.service..*.select*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.lhf.springboot.annotation.Master) " +
            "|| execution(* com.lhf.springboot.service..*.insert*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.add*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.update*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.edit*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.delete*(..)) " +
            "|| execution(* com.lhf.springboot.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else... 判断哪些需要读从数据库，其余的走主数据库
     */
     /*@Before("execution(* com.lhf.springboot.service.impl.*.*(..))")
     public void before(JoinPoint jp) {
         String methodName = jp.getSignature().getName();

         if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
            DBContextHolder.slave();
         }else {
            DBContextHolder.master();
         }
     }*/
}
