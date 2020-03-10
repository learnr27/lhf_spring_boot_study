package com.lhf.springboot.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyConfig
 * @Author: liuhefei
 * @Description: 自定义配置文件
 * @Date: 2019/7/29 16:43
 */
@Component
@ConfigurationProperties(prefix = "myconfig")  //前缀
@PropertySource(value = "classpath:myconfig.properties")   //加载自定义的配置文件资源
public class MyConfig {

    @Value("${content}")  //需要使用@Value注解来注入，否则为null
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
