package com.lhf.springboot.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: createTemplateUtils
 * @Author: liuhefei
 * @Description: 创建文件模板工具类
 * @Date: 2019/6/18 14:30
 */
public class CreateTemplateUtils {

    private static final String templateFolder = "spring-boot-docx-xml-freemark/src/main/resources/templates/";

    /**
     * 生成doc文档
     * @param ftlName  freemarker模板文件名
     * @param data   Map类型数据
     * @param docName 生成的doc文件名
     * @return
     */
    public static String createTemplate(String ftlName, Map<String, String> data, String docName){
        // 配置文件
        Configuration cfg;
        Template tpl;
        File docFile = null;
        FileOutputStream fos;
        Writer out;
        try {

            cfg = new Configuration();
            // 字符编码
            cfg.setDefaultEncoding("utf-8");
            // 模板目录
            cfg.setDirectoryForTemplateLoading(new File(templateFolder));
            // 加载模板文件
            //Template tpl = cfg.getTemplate("test.ftl");
            tpl = cfg.getTemplate(ftlName);

            // 准备数据
            /*Map<String, String> data = new HashMap<>();
            data.put("name", "东方不败");
            data.put("age", "26");
            data.put("phone", "135656512345");
            data.put("address", "南京");*/

            //tpl.setAutoImports(data);

            //System.out.println("tpl = " + tpl);

            // 生成文件
            //String outFIle = "spring-boot-docx-xml-freemark/doc/test.doc";
            String outFIle = "spring-boot-docx-xml-freemark/doc/" + docName;

            docFile = new File(outFIle);
            fos = new FileOutputStream(docFile);
            out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
            tpl.process(data, out);   //填充数据

            System.out.println("生成word文档：" + docFile.getAbsolutePath());



        }catch (Exception e){
            e.printStackTrace();
        }
        return docFile.getAbsolutePath();
    }


}
