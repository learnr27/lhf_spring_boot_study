package com.lhf.springboot.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 10:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
