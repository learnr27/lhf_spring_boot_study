package com.lhf.springboot.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @ClassName: FreemarkerUtil
 * @Author: liuhefei
 * @Description: Freemarker工具类
 * @Date: 2019/8/15 12:10
 */
public class FreemarkerUtil {
    private static final String path = FreemarkerUtil.class.getClassLoader().getResource("").getPath();

    public static String generateString(String templateFileName, String templateDirectory, Map<String, Object> datas)
            throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);

        // 设置默认编码
        configuration.setDefaultEncoding("UTF-8");

        // 设置模板所在文件夹
        //configuration.setDirectoryForTemplateLoading(new File(path + templateDirectory));
        configuration.setDirectoryForTemplateLoading(new File( templateDirectory));

        // 生成模板对象
        Template template = configuration.getTemplate(templateFileName);

        // 将datas写入模板并返回
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(datas, stringWriter);
            stringWriter.flush();
            return stringWriter.getBuffer().toString();
        }
    }
}
