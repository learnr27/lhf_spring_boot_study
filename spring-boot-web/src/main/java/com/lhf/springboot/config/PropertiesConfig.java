package com.lhf.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName: PropertiesConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 14:37
 */
@Data
@Component
public class PropertiesConfig {

    @Value("${com.lhf.title}")
    private String title;

    @Value("${com.lhf.description}")
    private String description;

}
