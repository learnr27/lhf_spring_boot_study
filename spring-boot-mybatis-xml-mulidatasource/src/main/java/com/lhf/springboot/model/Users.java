package com.lhf.springboot.model;

import com.lhf.springboot.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/26 18:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;
}
