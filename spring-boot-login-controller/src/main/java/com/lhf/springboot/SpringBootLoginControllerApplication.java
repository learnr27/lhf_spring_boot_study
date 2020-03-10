package com.lhf.springboot;

import com.lhf.springboot.filter.CompareKickOutFilter;
import com.lhf.springboot.filter.KickOutFilter;
import com.lhf.springboot.filter.QueueKickOutFilter;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.redisson.config.Config;

@SpringBootApplication
public class SpringBootLoginControllerApplication {

    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson() {
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec())
                .useSingleServer()
                .setAddress("redis://localhost:6379");
        return Redisson.create(config);
    }

    @ConditionalOnProperty(value = {"queue-filter.enabled"})
    @Bean
    public KickOutFilter queueKickOutFilter() {
        return new QueueKickOutFilter();
    }

    @ConditionalOnMissingBean(KickOutFilter.class)
    @Bean
    public KickOutFilter compareKickOutFilter() {
        return new CompareKickOutFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration(KickOutFilter kickOutFilter) {
        System.out.println(kickOutFilter);
        FilterRegistrationBean registration = new FilterRegistrationBean(kickOutFilter);
        registration.addUrlPatterns("/user/*");
        registration.setName("kickOutFilter");
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLoginControllerApplication.class, args);
    }

}
