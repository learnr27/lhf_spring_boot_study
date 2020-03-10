package com.lhf.springboot.model;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Article
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article", schema = "")
public class Article {

    @Id
    //@GeneratedValue(generator = "AUTO")
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;


    @Column(name = "press")
    private String press;

    @Column(name = "pub_date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pubDate;

    @Column(name = "status")
    private Integer status;


    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
