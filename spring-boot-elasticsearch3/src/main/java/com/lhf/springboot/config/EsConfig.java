package com.lhf.springboot.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.common.transport.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @ClassName: EsConfig
 * @Author: liuhefei
 * @Description: ES配置类
 * @Date: 2019/11/2 18:43
 */
@Configuration
public class EsConfig {

    @Bean
    public TransportClient client() throws UnknownHostException{
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);

        Settings settings = Settings.builder()
                .put("cluster.name", "wali")    //设置集群名称
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);   //设置节点

        return client;
    }
}
