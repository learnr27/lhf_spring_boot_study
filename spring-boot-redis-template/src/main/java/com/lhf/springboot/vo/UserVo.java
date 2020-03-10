package com.lhf.springboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserVo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/17 16:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    public  static final String Table = "t_user";

    private Integer id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String address;

}
