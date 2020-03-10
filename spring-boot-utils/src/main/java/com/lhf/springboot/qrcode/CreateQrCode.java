package com.lhf.springboot.qrcode;



import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * @ClassName: CreateQrCode
 * @Author: liuhefei
 * @Description: 生成二维码图片
 * @Date: 2019/11/7 10:49
 */
public class CreateQrCode {

    /**
     * 生成二维码Buffered
     * @param content  二维码内容
     * @return
     */
    public static BufferedImage qrCodeImage(String content){
        //二维码宽度
        int width = 140;
        //二维码高度
        int height = 140;

        try{
            Qrcode qrcode = new Qrcode();

            // 设置二维码的排错率 'L'：7%，'M'：15%，'Q'：25%，'H'：30%
            qrcode.setQrcodeErrorCorrect('M');
            qrcode.setQrcodeEncodeMode('B');

            //设置二维码的尺寸，尺寸越大，可存储的信息量越大
            qrcode.setQrcodeVersion(7);
            //设置图片的尺寸，格式
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //绘制二维码图片
            Graphics2D graphics2D = bufferedImage.createGraphics();
            //设置背景颜色
            graphics2D.setBackground(Color.WHITE);
            //创建二维码的矩形区域
            graphics2D.clearRect(0, 0, width, height);

            //设置二维码图片的颜色值
            graphics2D.setColor(Color.BLACK);  //Color.BLACK
            //二维码生成点阵的偏移量
            int pixoff = 2;
            //获取二维码内容的字节数组，并设置编码
            byte[] contentBytes = content.getBytes("UTF-8");
            //输出二维码
            if(contentBytes.length > 0 && contentBytes.length < 200){  //如果二维码内容在规定长度内
                boolean[][] codeOut = qrcode.calQrcode(contentBytes);
                for(int i = 0 ;i < codeOut.length; i++){
                    for(int j = 0; j < codeOut.length; j++){
                        if(codeOut[j][i]){
                            graphics2D.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
                        }
                    }
                }
            }
            graphics2D.dispose();
            bufferedImage.flush();
            return bufferedImage;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 在已有的二维码图片上加上logo图片
     * @param qrcodeImg
     * @param logoImg
     * @return
     */
    public static BufferedImage encodeImgLogo(File qrcodeImg, File logoImg){
        BufferedImage qrcode = null;
        try{
            if(!qrcodeImg.isFile() || !logoImg.isFile()){
                System.out.println("输入非图片");
                return null;
            }
            //读取二维码图片
            qrcode = ImageIO.read(qrcodeImg);
            //获取画笔
            Graphics2D graphics2D = qrcode.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(logoImg);
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int logoWidth = logo.getWidth(null) > qrcode.getWidth()*3 / 10 ? (qrcode.getWidth() * 3 / 10) : logo.getWidth(null);
            int logoHeight = logo.getHeight(null) > qrcode.getHeight() * 3 / 10 ? (qrcode.getHeight() * 3 / 10) : logo.getHeight(null);
            //确定二维码的中心位置坐标，设置logo图片放置的位置
            int x = (qrcode.getWidth() - logoWidth) / 2;
            int y = (qrcode.getHeight() - logoHeight) / 2;
            //开始合并绘制图片
            graphics2D.drawImage(logo, x, y, logoWidth, logoHeight, null);
            graphics2D.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
            //logo边框大小
            graphics2D.setStroke(new BasicStroke(2));
            //logo边框颜色
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawRect(x, y, logoWidth, logoHeight);
            graphics2D.dispose();
            logo.flush();
            qrcode.flush();

        }catch (Exception e){
            System.out.println("二维码绘制logo失败" + e);
        }
        return qrcode;
    }


    public static String getRandomString(int length){
        //1.  定义一个字符串（A-Z，a-z，0-9）即62个数字字母；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //2.  由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //3.  长度为几就循环几次
        for(int i=0; i<length; ++i){
            //从62个的数字或字母中选择
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }


    /**
     * 生成图片文件
     * @param bufferedImage  图片Buffered
     * @param imgPath  图片路径
     */
    public static String writeImage(BufferedImage bufferedImage, String imgPath){
        //生成二维码图片
        String imgName = getRandomString(6) + ".png";
        File file = new File(imgPath + imgName);

        System.out.println("img = " + file);
        try{
            ImageIO.write(bufferedImage, "png", file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return file.toString();
    }

    public static void main(String[] args) {
        //生成二维码
        //BufferedImage qrCode = qrCodeImage("https://www.imooc.com/u/1323320");
        BufferedImage qrCode = qrCodeImage("只愿君心似我心，定不负相思意");
        String qrCodeImg = writeImage(qrCode, "image/");

        //生成带有logo的二维码
        File qrcode = new File(qrCodeImg);
        File logo = new File("image\\logo.jpg");
        BufferedImage logoQrcode = encodeImgLogo(qrcode, logo);
        writeImage(logoQrcode, "image/");

    }



}
