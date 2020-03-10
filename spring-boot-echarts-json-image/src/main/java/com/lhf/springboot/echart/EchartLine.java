package com.lhf.springboot.echart;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

/**
 * @ClassName: EchartLine
 * @Author: liuhefei
 * @Description: Echarts 折线图
 * @Date: 2019/8/14 10:15
 */
public class EchartLine {

    /**
     * 生成折线图
     * @param isHorizontal  是否水平放置
     */
    public static void createLine(boolean isHorizontal){
        String[] types = { "邮件营销", "联盟广告", "视频广告" };
        int[][] xdatas = { { 120, 132, 101, 134, 90, 230, 210 }, { 220, 182, 191, 234, 290, 330, 310 }, { 150, 232, 201, 154, 190, 330, 410 } };
        String[] ydatas = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        String title = "广告数据";

        GsonOption option = new GsonOption();
        option.title().text(title).subtext("虚构").x("left");  //大标题，小标题，位置
        //提示工具
        option.tooltip().trigger(Trigger.axis);// 在轴上触发提示数据
        //工具栏
        option.toolbox().show(true).feature(Tool.saveAsImage); //显示保存为图片
        option.legend(types);  //图例

        CategoryAxis category = new CategoryAxis();  //轴分类
        category.data(ydatas);
        category.boundaryGap(false);// 起始和结束两端空白策略

        //循环数据
        for(int i=0;i<types.length;i++){
            Line line = new Line();  //三条线三个对象
            String type = types[i];
            line.name(type).stack("总量");
            for(int j=0;j<xdatas[i].length;j++){
                line.data(xdatas[i][j]);
            }
            option.series(line);
        }

        if(isHorizontal){  //横轴为类别，纵轴为值
            option.xAxis(category);  //x轴
            option.yAxis(new ValueAxis());  //y轴
        }else {  //横轴为值，纵轴为类别
            option.xAxis(new ValueAxis());  //x轴
            option.yAxis(category);  //y轴
        }
        System.out.println("option = " + option);
    }

    public static void main(String[] args) {
        createLine(true);
    }
}
