package com.lhf.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lhf.springboot.common.JsonResult;
import com.lhf.springboot.echarts.pojo.BarData;
import com.lhf.springboot.echarts.pojo.LinesData;
import com.lhf.springboot.echarts.pojo.PieData;
import com.lhf.springboot.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.FastDateFormat;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

/**
 * @ClassName: EchartsTemplateController
 * @Author: liuhefei
 * @Description: 使用模板生成柱状图和折线图以及饼图
 * @Date: 2019/9/26 14:23
 */
@Api(value = "Freemarker模板生成Echarts图表Api接口", tags = "模板生成图表Api")
@RequestMapping("/template")
@RestController
public class EchartsTemplateController {

    private final static Logger logger = Logger.getLogger(EchartsController.class);

    @Value("${img-url}")
    private String imgUrl;

    @Value("${img-url-path}")
    private String imgUrlPath;

    @Value("${request-url}")
    private String requestUrl;

    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("").getPath();
    private String temPath = path + "templates";

    @ApiOperation("模板生成柱状图")
    @RequestMapping(value = "/bar", method = RequestMethod.POST)
    public JsonResult createBarFtl(@RequestBody BarData barData){
        String title = barData.getTitle();
        String legendName = barData.getBarParamList().getLegendName();
        Object[] barName = barData.getBarParamList().getBarName();
        Object[] barValue = barData.getBarParamList().getBarValue();

        if(title == null || legendName == null || barName == null || barValue == null){
            return new JsonResult(-1, "参数异常");
        }

        String oldFilePath = null;
        String newFilePath = null;

        try {
            //组装参数
            HashMap<String, Object> datas = new HashMap<>();
            datas.put("title", title);
            datas.put("legend", legendName);
            datas.put("category", JSON.toJSONString(barName));
            datas.put("values", JSON.toJSONString(barValue));

            String option = FreemarkerUtil.generateString("barOption.ftl", temPath, datas);
            System.out.println("option = " + option);

            // 根据option参数
            String base64 =  EchartsUtil.generateEchartsBase64(option, requestUrl);
            System.out.println("BASE64:" + base64);

            long nowStr = Calendar.getInstance().getTimeInMillis();
            //图片名
            String imageName = "bar"+nowStr+ RandomUtils.getRandomString(4)+".png";
            logger.info("bar图片：" + imageName);

            File oldfile = FileUtil.generateImage(base64, imgUrl+imageName);
            File newfile = new File(imgUrl+"new"+imageName);
            oldFilePath = imgUrl+imageName;
            newFilePath = imgUrl+"new"+imageName;

            logger.info("file = " + oldfile);
            logger.info("oldFilePath = " + oldFilePath);
            logger.info("newFilePath = " + newFilePath);

            String logoText = "霜花似雪";

            //添加水印
            ImageUtil.markImageByText(logoText, oldFilePath, newFilePath, -15, new Color(190,190,190), "png");

        }catch (Exception e){
            logger.error("发生了异常，" + e);
            return new JsonResult(-1, "Fail");
        }
        return new JsonResult(1, newFilePath, "SUCCESS");
    }

    @ApiOperation("模板生成折线图")
    @RequestMapping(value = "/line", method = RequestMethod.POST)
    public JsonResult createLineFtl(@RequestBody LinesData linesData){
        String title = linesData.getTitle();
        String legendName = linesData.getLineParam().getLegendName();
        Object[] yData = linesData.getLineParam().getYdatas();
        List<Object[]> xData = linesData.getLineParam().getXdatas();
        Object[] xdatas = null;
        for(Object[] x : xData){
            xdatas = x;
        }


        if(title == null || legendName == null || yData == null || xData == null){
            return new JsonResult(-1, "参数异常");
        }

        String oldFilePath = null;
        String newFilePath = null;

        try {
            //组装参数
            HashMap<String, Object> datas = new HashMap<>();
            datas.put("title", title);
            datas.put("legend", legendName);
            datas.put("category", JSON.toJSONString(yData));
            datas.put("values", JSON.toJSONString(xdatas));

            String option = FreemarkerUtil.generateString("lineOption.ftl", temPath, datas);
            System.out.println("option = " + option);

            // 根据option参数
            String base64 =  EchartsUtil.generateEchartsBase64(option, requestUrl);
            System.out.println("BASE64:" + base64);

            long nowStr = Calendar.getInstance().getTimeInMillis();
            //图片名
            String imageName = "bar"+nowStr+ RandomUtils.getRandomString(4)+".png";
            logger.info("bar图片：" + imageName);

            File oldfile = FileUtil.generateImage(base64, imgUrl+imageName);
            File newfile = new File(imgUrl+"new"+imageName);
            oldFilePath = imgUrl+imageName;
            newFilePath = imgUrl+"new"+imageName;

            logger.info("file = " + oldfile);
            logger.info("oldFilePath = " + oldFilePath);
            logger.info("newFilePath = " + newFilePath);

            String logoText = "霜花似雪";

            //添加水印
            ImageUtil.markImageByText(logoText, oldFilePath, newFilePath, -15, new Color(190,190,190), "png");

        }catch (Exception e){
            logger.error("发生了异常，" + e.getMessage());
            return new JsonResult(-1, "Fail");
        }
        return new JsonResult(1, newFilePath, "SUCCESS");
    }

    @ApiOperation("模板生成饼图, html文件格式")
    @RequestMapping(value = "/pie", method = RequestMethod.POST)
    public JsonResult createPieFtl(@RequestBody PieData pieData) {
        String title = pieData.getTitle();
        String[] types = pieData.getTypes();
        String[] datas = pieData.getDatas();

        if (title == null || types == null || datas == null) {
            return new JsonResult(-1, "参数异常");
        }

        if(types.length != datas.length){
            return new JsonResult(-1, "数据不对应");
        }


        //组装参数
        Double data = null;
        Double sum = 0.0;

        DecimalFormat df = new DecimalFormat(".00");

        List<String> stringList = new ArrayList<>();
        String bfb = null;
        String typeParam = null;
        List<Map<String, Object>> listMap = new ArrayList<>();

        for(int i = 0;i<types.length;i++){
            data = Double.valueOf(datas[i]);
            System.out.println("data = " + data);
            sum += Double.valueOf(data);
        }
        System.out.println("sum = " + sum);

        for(int i =0 ;i<types.length;i++){
            data = Double.valueOf(datas[i]);
            bfb = String.valueOf(df.format(data/sum * 100));
            System.out.println("bfb = " + bfb);
            typeParam = types[i] + ":" + datas[i] + "(" + bfb + "%)";
            System.out.println(typeParam);
            //1.组装types
            stringList.add(typeParam);

            //2.组装datas
            Map<String, Object> mapStr = new HashMap<>();
            mapStr.put("name", typeParam);
            mapStr.put("value", datas[i]);

            listMap.add(mapStr);

        }

        System.out.println(stringList);
        System.out.println(listMap);

        try {
            //组装参数
            HashMap<String, Object> pieDatas = new HashMap<>();
            pieDatas.put("title", title);
            //pieDatas.put("types", JSON.toJSONString(types));
            pieDatas.put("types", JSON.toJSONString(stringList));
            pieDatas.put("datas", JSON.toJSONString(listMap));
            String option = FreemarkerUtil.generateString("pieOption1.ftl", temPath, pieDatas);
            System.out.println("option = " + option);

            Map<String, Object> pieOption = new HashMap<>();
            pieOption.put("option", option);
            String htmlPie = FreemarkerUtil.generateString("pieOption.ftl", temPath, pieOption);
            System.out.println("htmlPie = " + htmlPie);

            //写入html
            String nowStr = FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
            String imageName = "pie"+nowStr;
            File htmlFile = new File(imgUrl+imageName+".html");
            if(!htmlFile.exists()){
                htmlFile.createNewFile();
            }
            byte bytes[] = new byte[1024];
            bytes = htmlPie.getBytes();
            int b = bytes.length;  //字节长度
            FileOutputStream fos = new FileOutputStream(htmlFile);
            fos.write(bytes, 0, b);
            fos.write(bytes);
            fos.close();

        } catch (Exception e) {
            logger.error("发生了异常，" + e.getMessage());
            return new JsonResult(-1, "Fail");
        }
        return new JsonResult(1, "SUCCESS");
    }

    @ApiOperation("模板生成饼图")
    @RequestMapping(value = "/pieImg", method = RequestMethod.POST)
    public JsonResult createPieFtlImage(@RequestBody PieData pieData) {
        String title = pieData.getTitle();
        String[] types = pieData.getTypes();
        String[] datas = pieData.getDatas();

        if (title == null || types == null || datas == null) {
            return new JsonResult(-1, "参数异常");
        }

        if(types.length != datas.length){
            return new JsonResult(-1, "数据不对应");
        }


        //组装参数
        Double data = null;
        Double sum = 0.0;

        DecimalFormat df = new DecimalFormat(".00");

        List<String> stringList = new ArrayList<>();
        String bfb = null;
        String typeParam = null;
        List<Map<String, Object>> listMap = new ArrayList<>();

        for(int i = 0;i<types.length;i++){
            data = Double.valueOf(datas[i]);
            System.out.println("data = " + data);
            sum += Double.valueOf(data);
        }
        System.out.println("sum = " + sum);

        for(int i =0 ;i<types.length;i++){
            data = Double.valueOf(datas[i]);
            bfb = String.valueOf(df.format(data/sum * 100));
            System.out.println("bfb = " + bfb);
            typeParam = types[i] + ":" + datas[i] + "(" + bfb + "%)";
            System.out.println(typeParam);
            //1.组装types
            stringList.add(typeParam);

            //2.组装datas
            Map<String, Object> mapStr = new HashMap<>();
            mapStr.put("name", typeParam);
            mapStr.put("value", datas[i]);

            listMap.add(mapStr);

        }

        System.out.println(stringList);
        System.out.println(listMap);

        String oldFilePath = null;
        String newFilePath = null;

        try {
            //组装参数
            HashMap<String, Object> pieDatas = new HashMap<>();
            pieDatas.put("title", title);
            //pieDatas.put("types", JSON.toJSONString(types));
            pieDatas.put("types", JSON.toJSONString(stringList));
            pieDatas.put("datas", JSON.toJSONString(listMap));
            String option = FreemarkerUtil.generateString("pieOption1.ftl", temPath, pieDatas);
            System.out.println("option = " + option);

            long nowStr = Calendar.getInstance().getTimeInMillis();
            String imageName = "pie"+nowStr+ RandomUtils.getRandomString(4)+".png";

            Map<String, Object> opt = (Map<String, Object>) JSONObject.parse(option);
            PhantomJS js = new PhantomJS();
            js.setOpt(opt);
            js.setReqMethod("echarts");
            js.setFile(imgUrlPath+imageName);
            PhantomJSUtil.phantomJS(requestUrl, JSON.parseObject(JSON.toJSONString(js)));

            oldFilePath = imgUrlPath+imageName;
            newFilePath = imgUrlPath+"new"+imageName;

            logger.info("oldFilePath = " + oldFilePath);
            logger.info("newFilePath = " + newFilePath);

            String logoText = "霜花似雪";

            //添加水印
            ImageUtil.markImageByText(logoText, oldFilePath, newFilePath, -15, new Color(190,190,190), "png");

        } catch (Exception e) {
            logger.error("发生了异常，" + e.getMessage());
            return new JsonResult(-1, "Fail");
        }
        return new JsonResult(1, "SUCCESS");
    }

}
