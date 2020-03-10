package com.lhf.springboot.OCR;

import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @ClassName: OCRUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/30 16:29
 */
public class OCRUtil {

    /**
     *
     * @param srImage 图片路径
     * @param ZH_CN 是否使用中文训练库,true-是
     * @return 识别结果
     */
    public static String FindOCR(String srImage, boolean ZH_CN) {
        try {
            System.out.println("start");
            double start=System.currentTimeMillis();
            File imageFile = new File(srImage);
            if (!imageFile.exists()) {
                return "图片不存在";
            }
            BufferedImage textImage = ImageIO.read(imageFile);
            Tesseract instance= new Tesseract();
            instance.setDatapath("D:\\soft\\Tesseract-OCR\\tessdata");//设置训练库
            if (ZH_CN) {
                instance.setLanguage("chi_sim");//中文识别
            }else {
                instance.setLanguage("eng");//英文识别识别
            }
            String result = null;
            result = instance.doOCR(textImage);
            double end=System.currentTimeMillis();
            System.out.println("耗时"+(end-start)/1000+" s");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "发生未知错误";
        }
    }
    public static void main(String[] args) throws Exception {
        String result=FindOCR("E:\\code\\SpringBoot\\lhf_spring_boot_study\\spring-boot-tesseract\\doc\\image1.jpg",true);
        System.out.println(result);
    }

}
