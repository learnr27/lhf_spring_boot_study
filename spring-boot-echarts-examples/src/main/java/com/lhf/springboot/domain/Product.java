package com.lhf.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Product
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/3 16:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String productName;

    private Integer productNum;
}
