package com.lhf.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/9 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Girl implements Serializable {
    private static final long serialVersionUID = -2725847100582238033L;

    private Integer id;

    private String name;

    private Integer age;

    private String cupSize;

    private Integer weight;

    private Integer height;

    private String phone;

    private String address;

    private String content;

    private List<String> hobbies;

    private Map<String, Object> params;

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cupSize='" + cupSize + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", hobbies=" + hobbies +
                ", params=" + params +
                '}';
    }
}
