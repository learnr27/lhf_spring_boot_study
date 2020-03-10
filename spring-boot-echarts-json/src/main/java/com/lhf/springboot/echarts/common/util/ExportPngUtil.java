package com.lhf.springboot.echarts.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhf.springboot.echarts.api.model.jmh.*;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: ExportPngUtil
 * @Author: liuhefei
 * @Description: 导出png工具类
 * @Date: 2019/7/23 15:03
 */
public class ExportPngUtil {

    public static void postOption(String optionStr, String url) throws Exception{
        System.out.println("optionStr = " + optionStr + ", url = " + url);
        final MediaType TEXT = MediaType.parse("application/text; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(TEXT, optionStr);
        System.out.println("body = " + body);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        System.out.println("request = " + request);
        Response response = client.newCall(request).execute();
        System.out.println("response = " + response);
        if(response.isSuccessful()){
            System.out.println(response.body().string());
        }else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String generateOption(String titleStr, List<String> objects, List<String> dimensions,
                                        List<List<Double>> allData, String xunit) {
        Option option = new Option();
        //"title"
        Title title = new Title();
        title.setText(titleStr);
        //"tooltip"
        Tooltip tooltip = new Tooltip("axis", new AxisPointer("shadow"));
        //"legend"
        Legend legend = new Legend(objects);
        //"grid"
        Grid grid = new Grid(100);
        //"toolbox"
        Toolbox toolbox = new Toolbox(false, new Feature(new SaveAsImage("png")));
        //"xAxis"
        XAxis xAxis = new XAxis("value", xunit);
        //"yAxis"
        YAxis yAxis = new YAxis("category", false, dimensions, new YAxisLabel(20));
        //"series"
        List<Serie> series = new ArrayList<>();
        for(int i = 0;i< allData.size(); i++){
            Serie serie = new Serie();
            serie.setName(objects.get(i));
            serie.setType("bar");  //图表类型
            serie.setLabel(new Label(new Normal(true, 2)));
            serie.setData(allData.get(i));
            series.add(serie);
        }

        // 开始设置option
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setLegend(legend);
        option.setGrid(grid);
        option.setToolbox(toolbox);
        option.setXAxis(xAxis);
        option.setYAxis(yAxis);
        option.setSeries(series);

        String jsonString = null;
        try{
            jsonString = new ObjectMapper().writeValueAsString(option);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println("jsonString = " + jsonString);
        return jsonString;
    }
}
