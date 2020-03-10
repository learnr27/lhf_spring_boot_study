package com.lhf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


/**
 * @ClassName: Commodity
 * @Author: liuhefei
 * @Description: 商品实体
 * @Date: 2019/7/22 9:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "commodity")
public class Commodity implements Serializable {
    private static final long serialVersionUID = -5564828036302613260L;

    @Id
    private String skuId;  //商品id

    private String name;   //商品名称

    private String category;  //分类

    private Integer price;  //价格

    private String brand;  //品牌

    private Integer stock;
}
