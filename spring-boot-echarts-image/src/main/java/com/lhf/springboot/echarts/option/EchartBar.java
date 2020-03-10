package com.lhf.springboot.echarts.option;


import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.style.TextStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.lhf.springboot.echarts.EnhancedOption;
import com.lhf.springboot.echarts.pojo.BarData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: EchartBar
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 15:26
 */
public class EchartBar {

    /**
     * 生成单柱状图
     * @param //isHorizontal 是否水平放置
     * @param //color  柱状图颜色，可以不设置，默认为红色
     * @param //title  柱状图标题
     * @param //xdatas  横轴数据
     * @param //ydatas  纵轴数据
     * @return
     */
    public static GsonOption createBar(BarData barData){
        /*String[] citis = { "广州", "深圳", "珠海", "汕头", "韶关", "佛山" };
        int[] datas = { 6030, 7800, 5200, 3444, 2666, 5708 };
        String title = "地市数据";
        String[] colors = { "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)", "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)" };*/

        String title = barData.getTitle();
        boolean isHorizontal = barData.getHorizontal();
        Object[] xdatas = barData.getBarParamList().getBarName();
        Object[] ydatas = barData.getBarParamList().getBarValue();
        String legendName = barData.getBarParamList().getLegendName();

        Bar bar = new Bar();  //图类别（柱状图）
        //title
        EnhancedOption option = new EnhancedOption();
        option.title(title);  //标题
        option.title().textStyle().fontSize(15).color("red").fontWeight("bolder");

        //工具栏 toolbox
        /*option.toolbox().show(true).feature(Tool.mark,  //辅助线
                Tool.dataView, //数据视图
                new MagicType(Magic.line, Magic.bar), //线图，柱状图切换
                Tool.restore, //还原
                Tool.saveAsImage   //保存图片
        );
        option.toolbox().show(true).feature();*/

        //tooltip
        option.tooltip().show(true).formatter("{a}<br/>{b} : {c}");  //显示工具提示，设置提示格式

        //legend
        Legend legend = new Legend();
        TextStyle textStyle = new TextStyle();
        textStyle.color("red");
        textStyle.fontSize(15);
        textStyle.fontWeight("bolder");
        legend.setData(Collections.singletonList(legendName));
        legend.setTextStyle(textStyle);
        option.setLegend(legend);  //图例

        //axisLabel
        AxisLabel axisLabel = new AxisLabel();
        TextStyle textStyle1 = new TextStyle();
        textStyle1.fontSize(15);
        textStyle1.fontWeight("bolder");
        axisLabel.show(true);
        axisLabel.textStyle(textStyle1);

        //axisLine
        AxisLine axisLine = new AxisLine();
        LineStyle lineStyle = new LineStyle();
        lineStyle.color("#315070");
        lineStyle.width(4);
        axisLine.lineStyle(lineStyle);


        //xAxis
        CategoryAxis category = new CategoryAxis();// 轴分类
        category.data(xdatas);// 轴数据类别
        category.axisLabel(axisLabel);  // x轴文字样式
        category.axisLine(axisLine);  //x轴样式

        //yAxis
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.axisLabel().show(true).textStyle().fontSize(15).fontWeight("bolder");  //y轴文字样式
        valueAxis.axisLine().lineStyle().color("#315070").width(4);  //y轴样式

        //series
        bar.name(legendName);

        Normal normal = new Normal();
        normal.setShow(true);
        if(barData.getHorizontal() == false){
            normal.position(Position.inside);
        }else {
            normal.position(Position.top);
        }
        normal.color("green");
        normal.textStyle().color("red").fontSize(15).fontWeight("bolder");
        //bar.setBarWidth("40");  //柱条宽度
        //bar.setBarMaxWidth(100);  //柱条最大宽度
        //bar.setBarMinHeight(10);  //柱条最小高度
        bar.label().normal(normal);

        //循环数据
        for(int i = 0;i < xdatas.length;i++){
            int data = (int) ydatas[i];
            String color = "rgb(2,111,230)";
            //类目对应的柱状图
            Map<String, Object> map = new HashMap<>(2);
            map.put("value", data);

            map.put("itemStyle", new ItemStyle().normal(new Normal().color(color)));

            bar.data(map);
        }

        if(isHorizontal){  //横轴为类别，纵轴为值
            option.xAxis(category);  //x轴
            option.yAxis(valueAxis);  //y轴
        }else {  //横轴为值，纵轴为类别
            option.xAxis(valueAxis);  //x轴
            option.yAxis(category);  //y轴
        }

        option.series(bar);

        return option;
    }


}
