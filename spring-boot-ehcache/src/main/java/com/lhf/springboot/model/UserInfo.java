package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/11 16:01
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo implements Serializable {

    private Long id;

    private Integer age;

    private String nickName;

    private String phone;

    private String email;
}
