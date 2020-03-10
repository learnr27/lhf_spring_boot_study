package com.lhf.springboot.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ConfigurationFileInject
 * @Author: liuhefei
 * @Description:  通过从配置文件中获取属性值
 * @Date: 2019/7/24 15:32
 */
@Component
// 引入外部配置文件组：${app.configinject}的值来自config.properties。
// 如果相同第一个属性文件和第二属性文件存在相同key，则后一个属性文件里的key启作用
//resources\com.lhf.springboot\advance_value_inject.properties
@PropertySource({"classpath:com.lhf.springboot/config.properties",
        "classpath:com.lhf.springboot/config_${anotherfile.configinject}.properties"})
public class ConfigurationFileInject {
    @Value("${app.name}")
    private String appName; // 这里的值来自application.properties，spring boot启动时默认加载此文件

    @Value("${book.name}")
    private String bookName; // 注入第一个配置外部文件属性

    @Value("${book.name.placeholder}")
    private String bookNamePlaceholder; // 注入第二个配置外部文件属性

    @Autowired
    private Environment env;  // 注入环境变量对象，存储注入的属性值

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("bookName=").append(bookName).append("\r\n")
                .append("bookNamePlaceholder=").append(bookNamePlaceholder).append("\r\n")
                .append("appName=").append(appName).append("\r\n")
                .append("env=").append(env).append("\r\n")
                // 从eniroment中获取属性值
                .append("env=").append(env.getProperty("book.name.placeholder")).append("\r\n");
        return sb.toString();
    }
}
