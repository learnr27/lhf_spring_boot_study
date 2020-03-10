package com.lhf.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:13
 */
@Data
public class Girl {
    private Integer id;
    private String name;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String cupSize;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
