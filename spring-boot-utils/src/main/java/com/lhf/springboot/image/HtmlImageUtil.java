package com.lhf.springboot.image;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName: HtmlImageUtil
 * @Author: liuhefei
 * @Description: 将html页面转化为图片
 * @Date: 2019/10/9 12:36
 */
public class HtmlImageUtil {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String htmlFile = "E:\\code\\SpringBoot\\lhf_spring_boot_study\\spring-boot-utils\\src\\main\\resources\\templates\\image.html";
        html2Image(htmlFile, "D:/hello.png");
    }

    public static void html2Image(String htmlFile, String img) {
        File file = new File(htmlFile);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        // 加载html模版
        imageGenerator.loadHtml(sbf.toString());
        // 把html写入到图片
        imageGenerator.saveAsImage(img);
    }
}
