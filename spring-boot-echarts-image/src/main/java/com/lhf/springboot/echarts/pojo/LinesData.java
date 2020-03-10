package com.lhf.springboot.echarts.pojo;

/**
 * @ClassName: LineData
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 19:11
 */
public class LinesData {

    private String title;

    private LineParam lineParam;

    private boolean isHorizontal = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LineParam getLineParam() {
        return lineParam;
    }

    public void setLineParam(LineParam lineParam) {
        this.lineParam = lineParam;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }
}
