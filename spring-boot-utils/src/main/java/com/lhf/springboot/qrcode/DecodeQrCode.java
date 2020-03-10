package com.lhf.springboot.qrcode;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName: DecodeQrCode
 * @Author: liuhefei
 * @Description: 二维码解析
 * @Date: 2019/11/7 11:54
 */
public class DecodeQrCode {

    /**
     * 解析二维码
     */
    public static String decodeImg(String imgPath) {
        File imgFile = new File(imgPath);
        if (imgFile == null) {
            return null;
        }
        BufferedImage bufferedImage;
        String content = null;
        try {
            bufferedImage = ImageIO.read(imgFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            // decode 方法入参为 QRCodeImage，其是接口声明，需要实现该接口
            content = new String(decoder.decode(new CodeImg(bufferedImage)), "UTF-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /**
     * 实现 QRCodeImage 接口
     */
    static class CodeImg implements QRCodeImage {
        private BufferedImage image;
        public CodeImg(BufferedImage image) {
            super();
            this.image = image;
        }
        @Override
        public int getWidth() { return image.getWidth(); }
        @Override
        public int getHeight() { return image.getHeight(); }
        @Override
        public int getPixel(int x, int y) { return image.getRGB(x, y); }
        public BufferedImage getImage() { return image; }
        public void setImage(BufferedImage image) { this.image = image; }
    }

    public static void main(String[] args) {
        String content = decodeImg("image\\mMsgEM.png");
        System.out.println("content = " + content);
    }
}
