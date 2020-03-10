package com.lhf.springboot.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/30 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Girl implements Serializable {
    private static final long serialVersionUID = 7116495904549094291L;
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
