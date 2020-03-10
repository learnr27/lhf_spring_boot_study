package com.lhf.springboot.config;

import com.lhf.springboot.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName: PrimaryMongoConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 9:38
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.lhf.springboot.repository.primary", mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
