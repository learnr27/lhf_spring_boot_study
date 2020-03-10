package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: 将属性文件中的user信息封装成一个User对象
 * @Date: 2019/5/27 16:31
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private String name;

    private Integer age;

    private String sex;

    private Integer height;

    private Integer weight;

    private String phone;

    private String email;

    private String address;

    private String hobby;

    private String motto;


}
