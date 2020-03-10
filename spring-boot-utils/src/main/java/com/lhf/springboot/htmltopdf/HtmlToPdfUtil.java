package com.lhf.springboot.htmltopdf;

import com.itextpdf.text.pdf.BaseFont;
import com.lhf.springboot.file.FileUtil;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: HtmlToPdfUtil
 * @Author: liuhefei
 * @Description: 导出PDF转化工具
 * @Date: 2019/10/23 10:48
 */
public class HtmlToPdfUtil {

    /**
     * 生成 PDF 文件
     * @param out 输出流
     * @param html HTML字符串
     * @throws IOException IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("font/FangSong.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("font/PingFangSC.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
    }

    public static void main(String[] args) {
        String htmlStr = FileUtil.readFile("E:\\code\\SpringBoot\\lhf_spring_boot_study\\spring-boot-utils\\src\\main\\resources\\templates\\yulinlin.html");
        System.out.println("htmlStr = " + htmlStr);
        try{
            String path = "E:/html.pdf";
            OutputStream out = new FileOutputStream(path);
            createPDF(out, htmlStr);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
