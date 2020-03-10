package com.lhf.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: TransactionConfiguration
 * @Author: liuhefei
 * @Description: 事务配置
 * @Date: 2019/8/28 12:04
 */
@Configuration
@EnableTransactionManagement
@Slf4j
@AutoConfigureAfter({MybatisConfiguration.class})
public class TransactionConfiguration extends DataSourceTransactionManagerAutoConfiguration {

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource){
        log.info("事务配置");
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
