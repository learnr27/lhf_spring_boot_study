package com.lhf.springboot.test;

import com.alibaba.fastjson.JSON;
import com.lhf.springboot.util.EchartsUtil;
import com.lhf.springboot.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * @ClassName: CreateImgTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/15 12:18
 */
public class CreateImgTest {

    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("").getPath();

    public static void main(String[] args) throws ClientProtocolException, IOException, TemplateException {
        System.out.println("path = " + path);

        String url = "http://localhost:6666";

        String dirPath = System.getProperty("user.dir");
        System.out.println("dirPath = " + dirPath);
        String filePath = dirPath + "\\src\\main\\resources\\image";

        String temPath = path + "templates";

        // 变量
        String title = "水果";
        String[] categories = new String[] { "苹果", "香蕉", "西瓜" };
        int[] values = new int[] { 3, 2, 1 };

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串    E:\code\SpringBoot\lhf_spring_boot_study\spring-boot-echarts-image\src\main\resources\templates\option.ftl
        String option = FreemarkerUtil.generateString("option.ftl", temPath, datas);

        //String option = "{'calculable': true,'title': {'text': '深圳地区蒸发量和降水量','subtext': '纯属虚构'},'toolbox': {'feature': {'mark': {'show': true,'title': {'markUndo': '删除辅助线','markClear': '清空辅助线','mark': '辅助线开关'},'lineStyle': {'color': '#1e90ff','type': 'dashed','width': 2}},'dataView': {'show': true,'title': '数据视图','readOnly': false,'lang': ['数据视图','关闭','刷新']},'magicType': {'show': true,'title': {'bar': '柱形图切换','stack': '堆积','tiled': '平铺','line': '折线图切换'},'type': ['line','bar']},'restore': {'show': true,'title': '还原'},'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'trigger': 'axis'},'legend': {'data': ['蒸发量','降水量']},'xAxis': [{'type': 'category','data': ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']}],'yAxis': [{'type': 'value'}],'series': [{'name': '蒸发量','type': 'bar','markPoint': {'data': [{'name': '最大值','type': 'max'},{'name': '最小值','type': 'min'}]},'markLine': {'data': [{'name': '平均值','type': 'average'}]},'data': [2.0,4.9,7.0,23.2,25.6,76.7,135.6,162.2,32.6,20.0,6.4,3.3]},{'name': '降水量','type': 'bar','markPoint': {'data': [{'name': '年最高','value': 182.2,'xAxis': 7,'yAxis': 183,'symbolSize': 18},{'name': '年最低','value': 2.3,'xAxis': 11,'yAxis': 3}]},'markLine': {'data': [{'name': '平均值','type': 'average'}]},'data': [[2.6,5.9,9.0,26.4,28.7,70.7,175.6,182.2,48.7,18.8,6.0,2.3]]}]}";
        //String option = "{'title': {'text': '广告数据','subtext': '虚构','x': 'left'},'toolbox': {'feature': {'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'trigger': 'axis'},'legend': {'data': ['邮件营销','联盟广告','视频广告']},'xAxis': [{'type': 'category','boundaryGap': false,'data': ['周一','周二','周三','周四','周五','周六','周日']}],'yAxis': [{'type': 'value'}],'series': [{'name': '邮件营销','type': 'line','stack': '总量','data': [120,132,101,134,90,230,210]},{'name': '联盟广告','type': 'line','stack': '总量','data': [220,182,191,234,290,330,310]},{'name': '视频广告','type': 'line','stack': '总量','data': [150,232,201,154,190,330,410]}]}\n";
        //String option = "{'title': {'text': '地市数据'},'toolbox': {'feature': {'mark': {'show': true,'title': {'markUndo': '删除辅助线','markClear': '清空辅助线','mark': '辅助线开关'},'lineStyle': {'color': '#1e90ff','type': 'dashed','width': 2}},'dataView': {'show': true,'title': '数据视图','readOnly': false,'lang': ['数据视图','关闭','刷新']},'magicType': {'show': true,'title': {'bar': '柱形图切换','stack': '堆积','tiled': '平铺','line': '折线图切换'},'type': ['line','bar']},'restore': {'show': true,'title': '还原'},'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'formatter': '{a}<br/>{b} : {c}','show': true},'legend': {'data': ['地市数据']},'xAxis': [{'type': 'value'}],'yAxis': [{'type': 'category','data': ['广州','深圳','珠海','汕头','韶关','佛山']}],'series': [{'name': '地市数据','type': 'pie','data': [{'value': 6030,'itemStyle': {'normal': {'color': 'rgb(2,111,230)'}}},{'value': 7800,'itemStyle': {'normal': {'color': 'rgb(186,73,46)'}}},{'value': 5200,'itemStyle': {'normal': {'color': 'rgb(78,154,97)'}}},{'value': 3444,'itemStyle': {'normal': {'color': 'rgb(2,111,230)'}}},{'value': 2666,'itemStyle': {'normal': {'color': 'rgb(186,73,46)'}}},{'value': 5708,'itemStyle': {'normal': {'color': 'rgb(78,154,97)'}}}]}]}";
        //String option = "{'title': {'text': '地市数据'},'toolbox': {'feature': {'mark': {'show': true,'title': {'markUndo': '删除辅助线','markClear': '清空辅助线','mark': '辅助线开关'},'lineStyle': {'color': '#1e90ff','type': 'dashed','width': 2}},'dataView': {'show': true,'title': '数据视图','readOnly': false,'lang': ['数据视图','关闭','刷新']},'magicType': {'show': true,'title': {'bar': '柱形图切换','stack': '堆积','tiled': '平铺','line': '折线图切换'},'type': ['line','bar']},'restore': {'show': true,'title': '还原'},'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'formatter': '{a}<br/>{b} : {c}','show': true},'legend': {'data': ['地市数据']},'xAxis': [{'type': 'value'}],'yAxis': [{'type': 'category','data': ['广州','深圳','珠海','汕头','韶关','佛山','北京','天津','昆明','武汉']}],'series': [{'name': '地市数据','type': 'bar','data': [{'value': 6030},{'value': 7800},{'value': 5200},{'value': 3444},{'value': 2666},{'value': 5708},{'value': 9000},{'value': 8765},{'value': 7651},{'value': 6534}]}]}";
        //String option = "{'title': {'text': '地市数据'},'toolbox': {'feature': {'mark': {'show': true,'title': {'markUndo': '删除辅助线','markClear': '清空辅助线','mark': '辅助线开关'},'lineStyle': {'color': '#1e90ff','type': 'dashed','width': 2}},'dataView': {'show': true,'title': '数据视图','readOnly': false,'lang': ['数据视图','关闭','刷新']},'magicType': {'show': true,'title': {'bar': '柱形图切换','stack': '堆积','tiled': '平铺','line': '折线图切换'},'type': ['line','bar']},'restore': {'show': true,'title': '还原'},'saveAsImage': {'show': true,'title': '保存为图片','type': 'png','lang': ['点击保存']}},'show': true},'tooltip': {'formatter': '{a}<br/>{b} : {c}','show': true},'legend': {'data': ['地市数据']},'xAxis': [{'type': 'category','data': ['广州','深圳','珠海','汕头','韶关','佛山','北京','天津','昆明','武汉']}],'yAxis': [{'type': 'value'}],'series': [{'name': '地市数据','type': 'bar','data': [{'value': 6030,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 7800,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 5200,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 3444,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 2666,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 5708,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 9000,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 8765,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 7651,'itemStyle': {'normal': {'color': 'blue'}}},{'value': 6534,'itemStyle': {'normal': {'color': 'blue'}}}]}]}";
        //String option = "{'calculable':true,'legend':{'data':['邮件营销','联盟广告','视频广告'],'orient':'horizontal','x':'left'},'series':[{'center':['50%','60%'],'data':[{'name':'邮件营销','value':120},{'name':'联盟广告','value':90},{'name':'视频广告','value':150}],'name':'广告数据','radius':'55%','type':'pie'}],'title':{'subtext':'虚构','text':'广告数据','x':'center'},'toolbox':{'feature':{'mark':{'lineStyle':{'color':'#1e90ff','type':'dashed','width':2},'show':true,'title':{'markUndo':'删除辅助线','markClear':'清空辅助线','mark':'辅助线开关'}},'dataView':{'lang':['数据视图','关闭','刷新'],'readOnly':false,'show':true,'title':'数据视图'},'magicType':{'show':true,'title':{'bar':'柱形图切换','stack':'堆积','tiled':'平铺','line':'折线图切换'},'type':['pie','funnel']},'restore':{'show':true,'title':'还原'},'saveAsImage':{'lang':['点击保存'],'show':true,'title':'保存为图片','type':'png'}},'show':true},'tooltip':{'formatter':'{a} <br/>{b} : {c} ({d}%)','trigger':'item'}}\n";

        System.out.println("option = " + option);
        // 根据option参数
        String base64 =  EchartsUtil.generateEchartsBase64(option, url);

        System.out.println("BASE64:" + base64);
        generateImage(base64,  "image/test1.png");
    }

    public static void generateImage(String base64, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        try (OutputStream out = new FileOutputStream(path)){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
        }
    }
}
