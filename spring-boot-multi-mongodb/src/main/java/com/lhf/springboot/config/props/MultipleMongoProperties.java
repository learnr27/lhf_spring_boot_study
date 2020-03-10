package com.lhf.springboot.config.props;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: MultipleMongoProperties
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 9:39
 */
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {
    private MongoProperties primary = new MongoProperties();

    private MongoProperties secondary = new MongoProperties();

    public MongoProperties getPrimary() {
        return primary;
    }

    public void setPrimary(MongoProperties primary) {
        this.primary = primary;
    }

    public MongoProperties getSecondary() {
        return secondary;
    }

    public void setSecondary(MongoProperties secondary) {
        this.secondary = secondary;
    }
}
