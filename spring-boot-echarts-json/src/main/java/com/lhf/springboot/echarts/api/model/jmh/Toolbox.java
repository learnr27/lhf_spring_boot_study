package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Toolbox
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toolbox {

    private boolean show;
    private Feature feature;
}
