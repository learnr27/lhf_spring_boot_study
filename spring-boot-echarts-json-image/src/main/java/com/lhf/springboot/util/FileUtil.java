package com.lhf.springboot.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * @ClassName: FileUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/14 15:02
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    String basePath = "F:/echarts/";

    /**
     * 解析Base64位信息并输出到某个目录下面.
     *
     * @param base64Info base64串
     *  数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA ...  在"base64,"之后的才是图片信息
     * @return 文件地址
     */
    public String saveBase64Pic(String base64Info) {
        if (StringUtils.isEmpty(base64Info)) {
            return "";
        }

        String[] imgBase64 = base64Info.split("base64,");

        // 将图片输出到系统某目录.
        OutputStream out = null;

        String nowStr = FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
        String imageFile = basePath + nowStr + ".png";   //png格式的图片
        try {
            // 使用了Apache commons codec的包来解析Base64
            byte[] buffer = Base64.decodeBase64(imgBase64[1]);
            out = new FileOutputStream(imageFile);
            out.write(buffer);
        } catch (IOException e) {
            logger.error("解析Base64图片信息并保存到某目录下出错!", e);
            return "";
        } finally {
            IOUtils.closeQuietly(out);
        }
        System.out.println("imageFile = " + imageFile);
        return imageFile;
    }

    /**
     *
     * @Description 读取HTML文件，获取字符内容
     * @param filePath
     * @param charset
     * @return
     */
    public static String getHtmlContent(String filePath, String charset){

        String line = null;
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)),charset));
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取HTML文件，获取字符内容异常");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭流异常");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @Description HTML转Image
     * @param htmText HTML文本字符串
     * @return 希望生成的Image Location
     */
    /*public static String html2Img(String htmText, String saveImageLocation){

        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        try {
            imageGenerator.loadHtml(htmText);
            Thread.sleep(100);
            imageGenerator.getBufferedImage();
            Thread.sleep(200);
            imageGenerator.saveAsImage(saveImageLocation);
            //imageGenerator.saveAsHtmlWithMap("hello-world.html", saveImageLocation);

            //不需要转换位图的，下面三行可以不要
            *//*BufferedImage sourceImg = ImageIO.read(new File(saveImageLocation));
            sourceImg = transform_Gray24BitMap(sourceImg);
            ImageIO.write(sourceImg, "BMP", new File(saveImageLocation));*//*
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("将HTML文件转换成图片异常");
        }
        return saveImageLocation;
    }*/
}
