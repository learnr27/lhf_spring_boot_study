package com.lhf.springboot.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/5 16:08
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue strQueue(){
        return new Queue("String");
    }

    @Bean
    public Queue objectQueue(){
        return new Queue("object");
    }
}
