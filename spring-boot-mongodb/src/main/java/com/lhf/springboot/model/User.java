package com.lhf.springboot.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/30 9:29
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2277667206971749926L;

    private int id;

    private String name;

    private int age;

    private String phone;

    private String email;

    private Date birthday;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
