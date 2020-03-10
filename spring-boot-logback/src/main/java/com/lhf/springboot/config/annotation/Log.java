package com.lhf.springboot.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Log
 * @Author: liuhefei
 * @Description: 自定义注解
 * @Date: 2019/6/17 11:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    // 日志信息
    String value() default "";

    //是否忽略
    boolean ignore() default false;
}
