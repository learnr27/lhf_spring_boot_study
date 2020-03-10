package com.lhf.springboot.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MyProperties
 * @Author: liuhefei
 * @Description: 从application.properties 获取 配置
 * @Date: 2019/7/29 16:47
 */
@Component
@ConfigurationProperties(prefix = "web.str") //前缀   表示从配置文件中获取的数据转换为对象
public class MyProperties {

    @Value("${title}")
    private String title;

    @Value("${description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
