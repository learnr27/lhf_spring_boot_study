package com.lhf.springboot.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: 用户实体类
 * indexName:索引库的名称，类似数据库,需要注意的是indexName和type都必须是小写!!!
 * @Date: 2019/8/2 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "userindex", type = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -3925086033925369427L;

    private Long id;

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private String address;

    private String description;

    private Date createTime;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
