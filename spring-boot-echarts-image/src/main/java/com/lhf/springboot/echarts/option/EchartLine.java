package com.lhf.springboot.echarts.option;


import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.lhf.springboot.echarts.EnhancedOption;
import com.lhf.springboot.echarts.pojo.LinesData;

import java.util.List;

/**
 * @ClassName: EchartLine
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 19:10
 */
public class EchartLine {

    public static GsonOption createLine(LinesData linesData){

        //String[] types = { "邮件营销", "联盟广告", "视频广告" };
        //int[][] datas = { { 120, 132, 101, 134, 90, 230, 210 }, { 220, 182, 191, 234, 290, 330, 310 }, { 150, 232, 201, 154, 190, 330, 410 } };
        //String title = "广告数据";

        String title = linesData.getTitle();
        String legendName = linesData.getLineParam().getLegendName();
        String[] ydatas = linesData.getLineParam().getYdatas();
        boolean isHorizontal = linesData.isHorizontal();

        //String[] types = legendName;
        String[] types = {title};
        //int[][] xdatas = { { 120, 132, 101, 134, 90, 230, 210 }, { 220, 182, 191, 234, 290, 330, 310 }, { 150, 232, 201, 154, 190, 330, 410 } };
        List<Object[]> xdatas = linesData.getLineParam().getXdatas();


        Line line = new Line();  //三条线三个对象
        EnhancedOption option = new EnhancedOption();

        //文本样式
        TextStyle textStyle = new TextStyle();
        textStyle.color("red");
        textStyle.fontSize(15);
        textStyle.fontWeight("bolder");

        //title
        option.title().text(title).subtext("").x("left");  //大标题，小标题，位置
        //文本样式
        option.title().textStyle(textStyle);  //标题文本样式


        //提示工具 tooltip
        option.tooltip().trigger(Trigger.axis);// 在轴上触发提示数据
        //工具栏 toolbox
        //option.toolbox().show(true).feature(Tool.saveAsImage); //显示保存为图片

        //图例 legend
        Legend legend = new Legend();
        option.legend(legendName);  //图例
        option.legend().textStyle(textStyle); //图例文本样式

        //axisLabel
        AxisLabel axisLabel = new AxisLabel();
        TextStyle textStyle1 = new TextStyle();
        textStyle1.fontSize(15);
        textStyle1.fontWeight("bolder");
        axisLabel.show(true);
        axisLabel.textStyle(textStyle1);

        //axisLine
        AxisLine axisLine = new AxisLine();
        LineStyle lineStyle = new LineStyle();  //坐标轴样式
        lineStyle.color("#315070");
        lineStyle.width(4);
        axisLine.lineStyle(lineStyle);

        //xAxis
        CategoryAxis category = new CategoryAxis();  //轴分类
        category.data(ydatas);
        category.boundaryGap(false);// 起始和结束两端空白策略
        category.axisLabel(axisLabel);  //x轴文字样式
        category.axisLine(axisLine);  //x轴样式

        //yAxis
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().show(true).textStyle().fontSize(15).fontWeight("bolder");  //y轴文字样式
        valueAxis.axisLine().lineStyle().color("#315070").width(4);  //y轴样式


        line.setSmooth(true);   //平滑曲线
        line.setShowAllSymbol(true); //标志图形默认只有主轴显示（随主轴标签间隔隐藏策略），如需全部显示可把showAllSymbol设为true
        line.setSymbolSize("15");  //点大小
        line.setDataFilter("nearest"); //在折线图的数据数量大于实际显示的像素宽度（高度）的时候会启用优化
        line.setLegendHoverLink(true); //是否启用图例（legend）hover时的联动响应（高亮显示）

        Normal normal = new Normal();
        normal.setShow(true);  //是否显示数据
        normal.color("green");  //数据颜色
        normal.position(Position.right); //数据显示位置
        line.label().normal(normal);

        LineStyle lineStyle1 = new LineStyle();   //折线样式
        lineStyle1.color("green");  //线颜色
        lineStyle1.width(4);  //线宽
        line.lineStyle(lineStyle1);

        //循环数据
        for(int i=0;i<types.length;i++){
            String type = types[i];
            line.name(legendName.toString()).stack("总量");
            for(int j = 0; j< xdatas.get(i).length; j++){
                line.data(xdatas.get(i)[j]);
            }
            option.series(line);
        }

        if(isHorizontal){  //横轴为类别，纵轴为值
            option.xAxis(category);  //x轴
            option.yAxis(valueAxis);  //y轴
        }else {  //横轴为值，纵轴为类别
            option.xAxis(valueAxis);  //x轴
            option.yAxis(category);  //y轴
        }

        //System.out.println("option=" + option);

        return option;

    }
}
