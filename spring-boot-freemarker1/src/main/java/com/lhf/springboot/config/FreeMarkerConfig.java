package com.lhf.springboot.config;

import com.lhf.springboot.tags.MyTagDirective;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName: FreeMarkerConfig
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 16:09
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private MyTagDirective myTagDirective;

    @PostConstruct  //启动时加载
    public void setSharedVariable() throws TemplateModelException{
        System.out.println("====================初始化自定义tag配置====================");
        configuration.setSharedVariable("myTag", myTagDirective);
    }

}
