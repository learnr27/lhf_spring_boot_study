package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/22 16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private int age;
}
