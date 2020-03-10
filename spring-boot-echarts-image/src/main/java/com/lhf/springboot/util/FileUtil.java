package com.lhf.springboot.util;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: FileUtil
 * @Author: liuhefei
 * @Description: 将图片base64编码转化为图片
 * @Date: 2019/8/15 18:52
 */
public class FileUtil {

    public static File generateImage(String base64, String path) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        File file = new File(path);
        String fileName = file.getName();
        System.out.println("file = " + file);
        //创建临时文件
        //File tempFile = File.createTempFile(fileName, ".png");
        //FileOutputStream fos = new FileOutputStream(tempFile);*/

        try (OutputStream out = new FileOutputStream(path)){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            return file;
        }
       /* finally {
            //关闭临时文件
            fos.flush();
            fos.close();
            try {
                Thread.sleep(10000);
                tempFile.deleteOnExit();//程序退出时删除临时文件
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        */

    }

    public static void deleteFile(File file) {
        //File file = new File();
        String fileName = file.getName();
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
        }
    }
}
