package com.lhf.springboot.echart;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;

import com.lhf.springboot.util.FileUtil;

import gui.ava.html.image.generator.HtmlImageGenerator;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import gui.ava.html.Html2Image;


/**
 * @ClassName: EchartBar
 * @Author: liuhefei
 * @Description: Echarts 生成柱状图
 * @Date: 2019/8/14 10:15
 */
public class EchartBar {

    private final static String nowStr = FastDateFormat.getInstance("yyyy-MM-dd").format(new Date());
    private static String htmlUrl = "F:/echarts/";

    /**
     * 生成柱状图
     * @param isHorizontal  是否水平放置
     */
    public static String createBar(boolean isHorizontal){
        String[] citis = { "广州", "深圳", "珠海", "汕头", "韶关", "佛山", "北京", "天津", "昆明", "武汉" };
        int[] datas = { 6030, 7800, 5200, 3444, 2666, 5708, 9000, 8765, 7651, 6534 };
        String[] colors = { "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)", "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)" };
        String title = "地市数据";

        GsonOption option = new GsonOption();
        option.title(title);  //标题
        //工具栏
        option.toolbox().show(true).feature(Tool.mark,  //辅助线
                Tool.dataView, //数据视图
                new MagicType(Magic.line, Magic.bar), //线图，柱状图切换
                Tool.restore, //还原
                Tool.saveAsImage   //保存图片
                );
        option.tooltip().show(true).formatter("{a}<br/>{b} : {c}");  //显示工具提示，设置提示格式
        option.legend(title);  //图例
        System.out.println("option=" + option);

        Bar bar = new Bar(title);  //图类别（柱状图）
        CategoryAxis category = new CategoryAxis();// 轴分类
        category.data(citis);// 轴数据类别

        //循环数据
        for(int i = 0;i < citis.length;i++){
            int data = datas[i];
            //String color = colors[i];
            //类目对应的柱状图
            Map<String, Object> map = new HashMap<>(2);
            map.put("value", data);
            map.put("itemStyle", new ItemStyle().normal(new Normal().color("blue")));
            bar.data(map);
        }

        if(isHorizontal){  //横轴为类别，纵轴为值
            option.xAxis(category);  //x轴
            option.yAxis(new ValueAxis());  //y轴
        }else {  //横轴为值，纵轴为类别
            option.xAxis(new ValueAxis());  //x轴
            option.yAxis(category);  //y轴
        }

        option.series(bar);
        System.out.println("option=" + option);


        option.exportToHtml(htmlUrl, "EchartBar-"+ nowStr + ".html");
        String content = FileUtil.getHtmlContent(htmlUrl+"/EchartBar-"+nowStr+".html", "utf-8");
        System.out.println("html-content = " + content);
        return content;
    }

   public static void main(String[] args) {
        createBar(true);
    }

   /*
   public static void main(String[] args) {

        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        String htmlstr = FileUtil.getHtmlContent("F:/echarts/line.html", "utf-8");
        System.out.println("htmlstr = " + htmlstr);

        imageGenerator.loadHtml(htmlstr); // 加载html源码内容
        imageGenerator.getBufferedImage(); // 生成图片字符流
        imageGenerator.saveAsImage("F:/echarts/10001.png"); // 保存到本地
        System.out.println("----end----");

        // imageGenerator.saveAsHtmlWithMap("/home/demo1/dev/hello-world.html",
        // "/home/demo1/dev/hello-world.png");  //把图片转成网页，只是简单的img 引用


       /*Html2Image html2Image = Html2Image.fromHtml(htmlstr);
       html2Image.getImageRenderer().saveImage("F:/echarts/heb.jpg");
       */
       //html2Image.getHtmlImageMap().saveImageMapDocument("F:/echarts/line.html", "F:/echarts/heb.png");


       /*String charset = "GBK";
       String saveImageLocation = "F:/echarts/save.png";
       String htmText = FileUtil.getHtmlContent(htmlstr, charset);
       html2Img(htmText, saveImageLocation);*/

   //}

}
