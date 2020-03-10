package com.lhf.springboot.OCR;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;

/**
 * @ClassName: OCRTest
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/10/8 11:55
 */
public class OCRTest {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        File imageFile = new File(System.getProperty("user.dir") + "/spring-boot-tesseract/doc/test.png");
        Tesseract tessInst = new Tesseract();
        //tessInst.setDatapath(System.getProperty("user.dir") + "/tessdata");
        tessInst.setDatapath("D:\\soft\\Tesseract-OCR\\tessdata");//设置训练库
        tessInst.setLanguage("eng");// eng.traineddata is in /tessdata direcotry

        Rectangle rectangle = new Rectangle(0, 0, 763, 534);// recognize special area letters
        try {
            String result= tessInst.doOCR(imageFile, rectangle);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

    }
}
