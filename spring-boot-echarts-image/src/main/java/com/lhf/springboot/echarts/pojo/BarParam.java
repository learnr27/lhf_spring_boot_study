package com.lhf.springboot.echarts.pojo;

/**
 * @ClassName: BarParam
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 16:11
 */
public class BarParam {

    private Object[] barName;

    private Object[] barValue;

    private String legendName;

    public Object[] getBarName() {
        return barName;
    }

    public void setBarName(Object[] barName) {
        this.barName = barName;
    }

    public Object[] getBarValue() {
        return barValue;
    }

    public void setBarValue(Object[] barValue) {
        this.barValue = barValue;
    }

    public String getLegendName() {
        return legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }
}
