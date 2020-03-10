package com.lhf.springboot.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: TtlArticlePo
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 15:17
 */
@Data
@Accessors(chain = true)
public class ArticlePo {

    private Integer id;

    private String title;

    private String author;

    private String type;

    private String content;

    private String press;

    private String pubDate;

    private Integer status;
}
