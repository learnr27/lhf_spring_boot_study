package com.lhf.springboot.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ClassName: DownloadNetFileUtil
 * @Author: liuhefei
 * @Description: 下载网络文件工具类
 * @Date: 2019/10/21 16:02
 */
public class DownloadNetFileUtil {

    /**
     * 使用传统io stream 下载文件
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void download(String url, String saveDir, String fileName) {

        BufferedOutputStream bos = null;
        InputStream is = null;
        try {
            byte[] buff = new byte[8192];
            is = new URL(url).openStream();
            File file = new File(saveDir, fileName);
            file.getParentFile().mkdirs();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            while ( (count = is.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * 利用 commonio 库下载文件，依赖Apache Common IO ，官网 https://commons.apache.org/proper/commons-io/
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void downloadByApacheCommonIO(String url, String saveDir, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(saveDir, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用NIO下载文件， 需要 jdk 1.4+
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void downloadByNIO(String url, String saveDir, String fileName) {
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        FileChannel foutc = null;
        try {
            rbc = Channels.newChannel(new URL(url).openStream());
            File file = new File(saveDir, fileName);
            file.getParentFile().mkdirs();
            fos = new FileOutputStream(file);
            foutc = fos.getChannel();
            foutc.transferFrom(rbc, 0, Long.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (rbc != null) {
                try {
                    rbc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (foutc != null) {
                try {
                    foutc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 使用NIO下载文件， 需要 jdk 1.7+
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void downloadByNIO2(String url, String saveDir, String fileName) {
        try (InputStream ins = new URL(url).openStream()) {
            Path target = Paths.get(saveDir, fileName);
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static String downLoadFromUrl(String urlStr,  String savePath, String fileName) {
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取自己数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            System.out.println("info:"+url+" download success");
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static void downloadZip(String downloadUrl, String savePath, String fileName) {
        try {
            File file = new File(savePath + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            URL url = new URL(downloadUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            //log.error("download error ! url :{}, exception:{}", downloadUrl, e);
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        try{
            //downLoadFromUrl("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e","mv11.jpg","E:\\data\\interview\\");

            //downloadZip("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e", "E:\\data\\interview\\", "mv.jpg");

            //DownloadNetFileUtil.download("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e", "E:\\data\\interview\\", "mv11.jpg");

            //DownloadNetFileUtil.downloadByApacheCommonIO("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e", "E:\\data\\interview\\", "mv22.jpg");

            //DownloadNetFileUtil.downloadByNIO("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e", "E:\\data\\interview\\", "mv33.jpg");

            DownloadNetFileUtil.downloadByNIO2("https://img04.sogoucdn.com/app/a/100520021/58d4839e971af4e61e5848f25eff478e", "E:\\data\\interview\\", "mv44.jpg");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
