package com.lhf.springboot.json;

import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName: JsonToFile
 * @Author: liuhefei
 * @Description: 将json数据写入文件工具类
 * @Date: 2019/10/18 14:21
 */
public class JsonToFileUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonToFileUtil.class);

    private static boolean isTab = true;

    public static String stringToJSON(String strJson) {
        int tabNum = 0;
        StringBuffer jsonFormat = new StringBuffer();
        int length = strJson.length();
        for (int i = 0; i < length; i++) {
            char c = strJson.charAt(i);
            if (c == '{') {
                tabNum++;
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else if (c == '}') {
                tabNum--;
                jsonFormat.append("\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
                jsonFormat.append(c);
            } else if (c == ',') {
                jsonFormat.append(c + "\n");
                jsonFormat.append(getSpaceOrTab(tabNum));
            } else {
                jsonFormat.append(c);
            }
        }
        return jsonFormat.toString();
    }
    public static String getSpaceOrTab(int tabNum) {
        StringBuffer sbTab = new StringBuffer();
        for (int i = 0; i < tabNum; i++) {
            if (isTab) {
                sbTab.append('\t');
            } else {
                sbTab.append("    ");
            }
        }
        return sbTab.toString();
    }

    public static String jsonToFile(String filePath, String content){
        try{
            JSONArray jsonArray=new JSONArray();//创建JSONArray对象
            //获得当前时间
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHH");
            String jsonFileName = ldt.format(dtf) + ".json";
            String fileResultPath = filePath + jsonFileName;
            File file=new File(fileResultPath);
            if(!file.exists())//判断文件是否存在，若不存在则新建
            {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);//实例化FileOutputStream
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");//将字符流转换为字节流
            BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象

            //String jsonString=jsonArray.toString();//将jsonarray数组转化为字符串
            String JsonString=stringToJSON(content);//将jsonarrray字符串格式化
            bufferedWriter.write(JsonString);//将格式化的jsonarray字符串写入文件
            bufferedWriter.flush();//清空缓冲区，强制输出数据
            bufferedWriter.close();//关闭输出流
            System.out.println("json数据写入完毕");
            logger.info("数据成功写入文件");
            return fileResultPath;
        }catch (Exception e){
            logger.error("数据写入文件失败，发生了异常: " + e.getMessage());
            return " ";
        }
    }

    public static void main(String[] args) {
        String jsonString = "{\"datas\":[120,90,150,200],\"title\":\"广告数据\",\"types\":[\"邮件营销\",\"联盟广告\",\"视频广告\",\"游戏广告\"]}";
        String filePath = jsonToFile("F://", jsonString);
        System.out.println(filePath);
    }
}
