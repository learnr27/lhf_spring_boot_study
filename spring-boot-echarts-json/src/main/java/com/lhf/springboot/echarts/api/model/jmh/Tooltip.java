package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Tooltip
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tooltip {
    private String trigger;
    private AxisPointer axisPointer;
}
