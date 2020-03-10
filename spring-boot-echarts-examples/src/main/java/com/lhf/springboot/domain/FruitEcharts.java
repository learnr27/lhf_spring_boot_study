package com.lhf.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FruitEcharts
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/31 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FruitEcharts {

    private String name;  //水果名

    private Integer salesVolume;  //销售总额

}
