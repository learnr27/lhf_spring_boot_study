package com.lhf.springboot.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @ClassName: Article
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/9 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Integer id;

    private String title;

    private String author;

    private String content;

}
