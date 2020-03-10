package com.lhf.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: IpConfig
 * @Author: liuhefei
 * @Description: 自定义配置文件
 * @Date: 2019/7/29 16:54
 */

@Component//使用@Configuration也可以
@ConfigurationProperties(prefix = "ipconfig")//前缀
@PropertySource(value = "classpath:ipconfig.properties")//配置文件路径  在resource目录下
//@PropertySource(value = "file:ipconfig.proferties")//配置文件路径  在当前目录下
public class IpConfig {

    @Value("${ipWhiteList}")//需要使用@value注解来注入，否则是null
    private String ipWhiteList;

    /**
     * 获取ipWhiteList
     * @return  ipWhiteList
     */
    public String getIpWhiteList() {
        return ipWhiteList;
    }

    /**
     * 设置ipWhiteList
     * @param  ipWhiteList
     */
    public void setIpWhiteList(String ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }
}
