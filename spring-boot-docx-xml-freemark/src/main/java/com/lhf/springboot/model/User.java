package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/18 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;

    private String phone;

    private String address;
}
