package com.lhf.springboot.config;

import com.lhf.springboot.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName: SecondaryMongoConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 9:39
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.lhf.springboot.repository.secondary", mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {
}
