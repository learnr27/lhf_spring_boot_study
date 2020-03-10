package com.lhf.springboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Slave
 * @Author: liuhefei
 * @Description: 使用从库的注解
 * @Date: 2019/8/28 10:57
 */
@Target(ElementType.METHOD)   //作用于方法上
@Retention(RetentionPolicy.RUNTIME)  //永久保存
public @interface Slave {
}
