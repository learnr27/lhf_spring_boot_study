package com.lhf.springboot.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Book
 * @Author: liuhefei
 * @Description: 图书实体类
 * @Date: 2019/8/2 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "bookindex", type = "book")
public class Book implements Serializable {
    private static final long serialVersionUID = 2088981888010791050L;

    private Long id;

    private String bookName;  //书名

    private String author;  //作者

    private Integer price;  //价格

    private String content;  //图书内容

    private String producter;  //出版社

    private Date publishTime;  //出版时间

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
