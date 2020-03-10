package com.lhf.springboot.echarts.pojo;

import java.util.List;

/**
 * @ClassName: LineParam
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 19:35
 */
public class LineParam {

    private String legendName;

    private String[] ydatas;

    private List<Object[]> xdatas;

    public String getLegendName() {
        return legendName;
    }

    public void setLegendName(String legendName) {
        this.legendName = legendName;
    }

    public String[] getYdatas() {
        return ydatas;
    }

    public void setYdatas(String[] ydatas) {
        this.ydatas = ydatas;
    }

    public List<Object[]> getXdatas() {
        return xdatas;
    }

    public void setXdatas(List<Object[]> xdatas) {
        this.xdatas = xdatas;
    }
}
