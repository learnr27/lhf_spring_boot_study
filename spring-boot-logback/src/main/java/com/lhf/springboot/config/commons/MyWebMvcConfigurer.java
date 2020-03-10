package com.lhf.springboot.config.commons;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: MyWebMvcConfigurer
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/17 11:26
 */
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 多个拦截器组成一个拦截器链
         * addPathPatterns 用于添加拦截规则
         * excludePathPatterns 用于排除拦截
         */
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
