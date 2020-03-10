package com.lhf.springboot.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/31 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    private String name;

    private String password;

    private String phone;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
