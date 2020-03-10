package com.lhf.springboot.model;

import com.lhf.springboot.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Users
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/3 17:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;


    public Users(String userName, String passWord, UserSexEnum userSex) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
    }
}
