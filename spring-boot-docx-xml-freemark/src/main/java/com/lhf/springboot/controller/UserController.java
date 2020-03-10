package com.lhf.springboot.controller;

import com.lhf.springboot.model.User;
import com.lhf.springboot.util.CreateTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TestController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/6/18 10:53
 */
@RestController
public class UserController {

    private static final String templateFolder = "spring-boot-docx-xml-freemark/src/main/resources/templates/";

    @RequestMapping(value = "/test")
    public User userInfo(){
        User user = new User();
        user.setName("刘豆豆");
        user.setAge(22);
        user.setPhone("18295514444");
        user.setAddress("广东深圳");


        return user;
    }

    /**
     * 生成doc文档
     * @param args
     */
    public static void main(String[] args) {
        /*
        try {

            // 配置文件
            Configuration cfg = new Configuration();
            // 字符编码
            cfg.setDefaultEncoding("utf-8");
            // 模板目录
            cfg.setDirectoryForTemplateLoading(new File(templateFolder));
            // 加载模板文件
            //Template tpl = cfg.getTemplate("test.ftl");
            Template tpl = cfg.getTemplate("test2.ftl");
            // 准备数据
            Map<String, String> data = new HashMap<>();
            data.put("name", "东方不败");
            data.put("age", "26");
            data.put("phone", "135656512345");
            data.put("address", "南京");
            tpl.setAutoImports(data);
            System.out.println("tpl = " + tpl);

            // 生成文件
            //String outFIle = "spring-boot-docx-xml-freemark/doc/test.doc";
            String outFIle = "spring-boot-docx-xml-freemark/doc/test2.doc";

            File docFile = new File(outFIle);
            FileOutputStream fos = new FileOutputStream(docFile);
            Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
            tpl.process(data, out);   //填充数据

            System.out.println("生成word文档：" + docFile.getAbsolutePath());

        }catch (Exception e){
            e.printStackTrace();
        }*/

        /*
        String ftlName = "test.ftl";
        // 准备数据
        Map<String, String> data = new HashMap<>();
        data.put("name", "东方不败");
        data.put("age", "26");
        data.put("phone", "135656512345");
        data.put("address", "南京");
        String docName = "test.doc";

        String docFile = CreateTemplateUtils.createTemplate(ftlName, data, docName);
        System.out.println("docFile = " + docFile);
        */

        /*
        String ftlName = "Web.ftl";
        Map<String, String> data = new HashMap<>();
        data.put("userAcount", "v_llu=00031840=duncanzhang");

        String docName = "Web.config";

        String docFile = CreateTemplateUtils.createTemplate(ftlName, data, docName);


        System.out.println("docFile = " + docFile);
        */

        String ftlName = "users.ftl";
        Map<String, String> data = new HashMap<>();
        data.put("name", "小明");
        data.put("age", "23");
        data.put("className", "软件一班");

        data.put("name", "小花");
        data.put("age", "22");
        data.put("className", "软件二班");

        data.put("name", "小张");
        data.put("age", "23");
        data.put("className", "软件三班");

        String docName = "users.xls";

        String docFile = CreateTemplateUtils.createTemplate(ftlName, data, docName);


        System.out.println("docFile = " + docFile);

    }
}
