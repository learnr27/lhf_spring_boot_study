package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: YAxis
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YAxis {
    private String type;
    private boolean inverse;
    private List<String> data;
    private YAxisLabel axisLabel;
}
