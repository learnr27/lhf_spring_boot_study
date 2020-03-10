package com.lhf.springboot.test;

import com.lhf.springboot.freemarker.FreemarkerUtil;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName: CreateTemplate
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/16 10:50
 */
public class CreateTemplate {

    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("").getPath();


    public static void createMessage(){
        String temPath = path + "templates";
        try {
            String title = "小主，你终于来了";
            List<String> list = new ArrayList<String>();
            list.add("<a href='https://www.baidu.com'>来来来，喝完这杯还有一杯</a>");
            list.add("<a href='https://www.baidu.com'>再醉一回</a>");
            list.add("<a href='https://www.baidu.com'>千杯不倒</a>");
            String message = "酒入愁肠，化作相思雨";

            // 模板参数
            HashMap<String, Object> datas = new HashMap<>();
            datas.put("title", title);
            datas.put("list", list);
            datas.put("message", message);

            String newTemplate = FreemarkerUtil.generateString("message.ftl", temPath, datas);

            System.out.println(newTemplate);
        }catch (Exception e){

            throw  new RuntimeException("生成模板失败！" + e);
        }
    }

    public static void main(String[] args) {

        createMessage();
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
    }
}
