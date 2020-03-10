package com.lhf.springboot.echarts.pojo;

import lombok.Data;

/**
 * @ClassName: BarData
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 16:08
 */
public class BarData {

    private String title;  //标题


    private BarParam barParamList;

    private Boolean isHorizontal;  //是否水平放置


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BarParam getBarParamList() {
        return barParamList;
    }

    public void setBarParamList(BarParam barParamList) {
        this.barParamList = barParamList;
    }

    public Boolean getHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(Boolean horizontal) {
        isHorizontal = horizontal;
    }
}
