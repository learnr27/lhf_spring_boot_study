package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Option
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private Grid grid;
    private Toolbox toolbox;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Serie> series;
}
