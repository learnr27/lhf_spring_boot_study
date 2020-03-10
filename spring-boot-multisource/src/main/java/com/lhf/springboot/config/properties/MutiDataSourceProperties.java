package com.lhf.springboot.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MutiDataSourceProperties
 * @Author: liuhefei
 * @Description: 多数据源的配置
 * @Date: 2019/7/31 17:48
 */
@Component
@ConfigurationProperties(prefix = "biz.datasource")
public class MutiDataSourceProperties {

    private String url;

    private String username;

    private String password;

    public void config(DruidDataSource dataSource) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
