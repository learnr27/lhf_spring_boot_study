package com.lhf.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.abel533.echarts.json.GsonOption;
import com.lhf.springboot.common.JsonResult;
import com.lhf.springboot.echarts.option.EchartBar;
import com.lhf.springboot.echarts.option.EchartLine;
import com.lhf.springboot.echarts.option.EchartPie;
import com.lhf.springboot.echarts.pojo.BarData;
import com.lhf.springboot.echarts.pojo.LinesData;
import com.lhf.springboot.echarts.pojo.PieData;
import com.lhf.springboot.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: EchartsController
 * @Author: liuhefei
 * @Description: 利用Java代码生成柱状图和折线图
 * @Date: 2019/9/26 10:37
 */
@Api(value = "生成Echarts图表API接口", tags = "Echarts图表")
@RequestMapping("/echarts")
@RestController
public class EchartsController {

    private final static Logger logger = Logger.getLogger(EchartsController.class);

    @Value("${img-url}")
    private String imgUrl;

    @Value("${img-url-path}")
    private String imgUrlPath;

    @Value("${request-url}")
    private String requestUrl;


    @ApiOperation(value = "生成柱状图")
    @RequestMapping(value = "/bar", method = RequestMethod.POST)
    public JsonResult createBar(@RequestBody BarData barData){

        GsonOption option = EchartBar.createBar(barData);

        String optionStr = JSONObject.toJSONString(option);
        if(optionStr == null || "".equals(optionStr)){
            return new JsonResult(-1, "Fail");
        }
        logger.info("bar-optionStr = " + optionStr);

        File oldfile = null;
        File newfile = null;
        String oldFilePath = null;
        String newFilePath = null;

        try {
            // 根据option参数发起请求，转换为base64
            String base64 =  EchartsUtil.generateEchartsBase64(optionStr, requestUrl);

            long nowStr = Calendar.getInstance().getTimeInMillis();
            //图片名
            String imageName = "bar"+nowStr+ RandomUtils.getRandomString(4)+".png";
            logger.info("bar图片：" + imageName);

            oldfile = FileUtil.generateImage(base64, imgUrl+imageName);
            newfile = new File(imgUrl+"new"+imageName);
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
        return new JsonResult(1,  newFilePath, "SUCCESS");
    }


    @ApiOperation(value = "生成折线图")
    @RequestMapping(value = "/line", method = RequestMethod.POST)
    public JsonResult createLine(@RequestBody LinesData linesData){

        GsonOption option = (GsonOption) EchartLine.createLine(linesData);

        String optionStr = JSONObject.toJSONString(option);

        if(optionStr == null || "".equals(optionStr)){
            return new JsonResult(-1, "Fail");
        }

        logger.info("line-optionStr = " + optionStr);

        File oldfile = null;
        File newfile = null;
        String oldFilePath = null;
        String newFilePath = null;

        try{
            // 根据option参数发起请求，转换为base64
            String base64Code =  EchartsUtil.generateEchartsBase64(optionStr, requestUrl);


            /*时间格式化*/
            //String nowStr = FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
            long nowStr = Calendar.getInstance().getTimeInMillis();
            String imageName = "line"+nowStr+ RandomUtils.getRandomString(4)+".png";
            logger.info("line图片：" + imageName);

            oldfile = FileUtil.generateImage(base64Code, imgUrl+imageName);
            newfile = new File(imgUrl+"new"+imageName);
            logger.info("file = " + oldfile);

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
        return new JsonResult(1,  newFilePath, "SUCCESS");
    }

    /**
     * 由于插件的问题，生成饼图会一直失败
     * @param pieData
     * @return
     */
    @ApiOperation(value = "生成饼图")
    @RequestMapping(value = "/pie", method = RequestMethod.POST)
    public JsonResult createPie(@RequestBody PieData pieData){

        GsonOption option = (GsonOption) EchartPie.createPie(pieData);
        String optionStr = JSONObject.toJSONString(option);
        if(optionStr == null || "".equals(optionStr)){
            return new JsonResult(-1, "Fail");
        }
        logger.info("pie-optionStr = " + optionStr);

        Map<String, Object> opt = (Map<String, Object>) JSONObject.parse(optionStr);
        System.out.println("opt = " + opt);

        File file = null;
        String oldFilePath = null;
        String newFilePath = null;

        try {
            long nowStr = Calendar.getInstance().getTimeInMillis();
            String imageName = "pie"+nowStr+ RandomUtils.getRandomString(4)+".png";

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
        return new JsonResult(1,  file, "SUCCESS");
    }


}
