package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Serie
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Serie {
    private String name;
    private String type;
    private List<Double> data;
    private Label label;
}
