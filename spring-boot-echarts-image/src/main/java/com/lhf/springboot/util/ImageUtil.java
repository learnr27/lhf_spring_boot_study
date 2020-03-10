package com.lhf.springboot.util;

import org.jboss.logging.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName: ImageUtil
 * @Author: liuhefei
 * @Description: 给图片添加水印工具类
 * @Date: 2019/9/10 12:32
 */
public class ImageUtil {
    private final static Logger logger = Logger.getLogger(ImageUtil.class);

    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     * @param logoText 要写入的文字
     * @param srcImgPath 源图片路径
     * @param newImagePath 新图片路径
     * @param degree 旋转角度
     * @param color  字体颜色
     * @param formaName 图片后缀
     */
    public static void markImageByText(String logoText, String srcImgPath, String newImagePath, Integer degree, Color color, String formaName) {
        logger.info("入参logoText = " + logoText + " , srcImgPath = " + srcImgPath + " , newImagePath = " + newImagePath);
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),  buffImg.getWidth()/2,buffImg.getHeight() /2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(new Font("宋体", Font.BOLD, buffImg.getHeight() /6));
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.15f));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText,  buffImg.getWidth()/4 , buffImg.getHeight()/2);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(newImagePath);
            ImageIO.write(buffImg, formaName, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        markImageByText("刘河飞","F:\\echarts\\bar1567600917165oNeL.png","F:\\echarts\\new\\bar1567600917135oNeL.png",45,new Color(200,10,1),"png");
    }


}
