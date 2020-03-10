package com.lhf.springboot.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName: GetSpringBean
 * @Author: liuhefei
 * @Description: Spring动态获取bean实现
 * @Date: 2019/11/6 15:57
 */
public class GetSpringBean implements ApplicationContextAware {

    private static ApplicationContext context;

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> c) {

        return context.getBean(c);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          if(applicationContext != null){
              context = applicationContext;
          }
    }
}
