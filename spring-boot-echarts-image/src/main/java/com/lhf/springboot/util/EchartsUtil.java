package com.lhf.springboot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: EchartsUtil
 * @Author: liuhefei
 * @Description: 生成echarts工具类：将生成的option转化为base64编码
 * @Date: 2019/8/15 12:10
 */
public class EchartsUtil {
    //private static String url = "http://localhost:6666";

    private static final String SUCCESS_CODE = "1";

    public static String generateEchartsBase64(String option, String url) throws ClientProtocolException, IOException {
        String base64 = "";
        if (option == null) {
            return base64;
        }
        option = option.replaceAll("\\s+", "").replaceAll("\"", "'");

        // 将option字符串作为参数发送给echartsConvert服务器
        Map<String, String> params = new HashMap<>();
        params.put("opt", option);
        String response = HttpUtil.post(url, params, "utf-8");

        // 解析echartsConvert响应
        JSONObject responseJson = JSON.parseObject(response);
        String code = responseJson.getString("code");

        // 如果echartsConvert正常返回
        if (SUCCESS_CODE.equals(code)) {
            base64 = responseJson.getString("data");
        }
        // 未正常返回
        else {
            String string = responseJson.getString("msg");
            throw new RuntimeException(string);
        }

        return base64;
    }


    public static String generateEchartsPieBase64(String option, String url) throws Exception {

        String base64 = "";
        if (option == null) {
            return base64;
        }
        option = option.replaceAll("\\s+", "").replaceAll("\"", "'");


        // 将option字符串作为参数发送给echartsConvert服务器
        Map<String, String> params = new HashMap<>();
        params.put("opt", option);

        String strParam = JSONObject.toJSONString(params);

        //String response = HttpUtil.send(url, jsonParam, "utf-8");
        //String response = HttpUtil.post(url, params, "utf-8");
        String response = HttpUtil.doPost(url, option);
        System.out.println("response = " + response);

        // 解析echartsConvert响应
        JSONObject responseJson = JSON.parseObject(response);
        String code = responseJson.getString("code");

        // 如果echartsConvert正常返回
        if (SUCCESS_CODE.equals(code)) {
            base64 = responseJson.getString("data");
        }
        // 未正常返回
        else {
            String string = responseJson.getString("msg");
            throw new RuntimeException(string);
        }

        return base64;
    }
}
