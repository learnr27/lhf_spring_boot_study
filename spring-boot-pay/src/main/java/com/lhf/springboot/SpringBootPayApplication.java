package com.lhf.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 支付主控(启动的时候一定要把main方法的注释去掉，配置好支付宝、微信以及银联相关参数)
 * 创建者 科帮网
 * 创建时间 2017年7月27日
 * 启动   java -jar spring-boot-pay.jar --server.port=8886
 * linux 下 后台启动  nohup java -jar spring-boot-pay.jar --server.port=8886 &
 *
 */
@EnableDubboConfiguration
@SpringBootApplication
public class SpringBootPayApplication extends WebMvcConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SpringBootPayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPayApplication.class, args);
        logger.info("支付项目启动！");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cert/**").addResourceLocations(
                "classpath:/cert/");
        super.addResourceHandlers(registry);
        logger.info("自定义静态资源目录,这只是个Demo,生产肯定不会暴露");
    }
}
