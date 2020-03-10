package com.lhf.springboot.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Girl
 * @Author: liuhefei
 * @Description: Girl pojo类
 * @Date: 2019/7/25 16:06
 */
@Data
@Table(name="girl")
public class Girl implements Serializable {

    private static final long serialVersionUID = -8882399257361782573L;

    @Id
    private int id;
    private String name; // 姓名
    private int age;  //年龄
    private int height;  //身高
    private int weight;  //体重
    private String cupSize;  //罩杯

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
