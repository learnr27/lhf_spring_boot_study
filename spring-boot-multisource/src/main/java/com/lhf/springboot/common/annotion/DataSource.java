package com.lhf.springboot.common.annotion;

import java.lang.annotation.*;

/**
 * @ClassName: DataSource
 * @Author: liuhefei
 * @Description: 多数据源标识
 * @Date: 2019/7/31 17:16
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {
    String name() default "";
}
