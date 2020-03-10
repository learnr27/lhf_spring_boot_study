package com.lhf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories  //启用Elasticsearch存储库
public class SpringBootElasticsearch2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearch2Application.class, args);
    }

    //如果您不需要在启动时插入数据，则可以通过将属性initial-import由enabled转变为false来禁用该过程。
    @Bean
    @ConditionalOnProperty("initial-import.enabled")
    public SampleDataSet dataSet() {
        return new SampleDataSet();
    }
}
