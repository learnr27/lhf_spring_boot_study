package com.lhf.springboot.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @ClassName: FreeMarkerTemplateUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/12 16:22
 */
public class FreeMarkerTemplateUtil {

    public String getEmailHtml(Map map, String templateName){
        String htmlText = "";
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        try{
            //加载模板路径，此处对应在resource目录下
            configuration.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "ftl");
            //或者
            //configuration.setClassLoaderForTemplateLoading(this.getClass(), "/com/mail/ftl");

            //获取对应名称的模板
            Template template = configuration.getTemplate(templateName);
            //渲染模板为html
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return htmlText;
    }

    /**
     * 输出到控制台
     * @param name
     * @param root
     * @throws TemplateModelException
     * @throws IOException
     */
    public void print(String name, Map<String, Object> root) throws TemplateException, IOException{
        //通过Template可以将模板文件输出到相应的流
        Template template = this.getTemplate(name);
        template.process(root, new PrintWriter(System.out));
    }

    /**
     * 获取模板信息
     * @param name 模板名
     * @return
     */
    public Template getTemplate(String name){
        //通过freemarkerd Configuration获取相应的ftl
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        //设定去哪里读取相应的ftl模板文件，指定模板路径
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(), "ftl");
        try{
            //在模板文件目录中找到名称为name的文件
            Template template = cfg.getTemplate(name);
            return template;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void fPrint(String name, Map<String, Object> root, String outFile){
        FileWriter out = null;
        try{
            out = new FileWriter(new File("E:/" + outFile));
            //获取模板
            Template template = this.getTemplate(name);
            //设置模板编码
            template.setEncoding("utf-8");
            try {
                //输出
                template.process(root, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e2) {
            }
        }
    }

}
