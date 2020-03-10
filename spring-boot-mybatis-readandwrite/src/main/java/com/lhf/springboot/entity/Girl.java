package com.lhf.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/28 16:54
 */
@Data
public class Girl implements Serializable {

    private static final long serialVersionUID = -8882399257361782573L;

    private Long id;
    private String name; // 姓名
    private Integer age;  //年龄
    private Integer height;  //身高
    private Integer weight;  //体重
    private String cupSize;  //罩杯

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
