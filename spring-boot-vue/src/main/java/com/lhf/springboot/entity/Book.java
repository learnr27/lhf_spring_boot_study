package com.lhf.springboot.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @ClassName: Book
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/28 14:50
 */
@Data
public class Book {

    private Integer id;
    private String name;
    private String author;
    private String publish;
    private int pages;
    private double price;
    private BookCase bookCase;
    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
