package com.lhf.springboot.model;

import lombok.Data;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/10 11:48
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
