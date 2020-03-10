package com.lhf.springboot.echarts.api.model.jmh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: Legend
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Legend {

    private List<String> data;
}
