package com.lhf.springboot.echarts.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: EchartsData
 * @Author: liuhefei
 * @Description:
 * @Date: 2019/7/23 14:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsData {

    //图表类型
    private String echartType;

    //图表数据
    private String option;

}
