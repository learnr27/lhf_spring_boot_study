package com.lhf.springboot.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * @ClassName: DownloadFileUtil
 * @Author: liuhefei
 * @Description: 下载本地文件工具类
 * @Date: 2019/10/18 17:40
 */
public class DownloadFileUtil {

    private final static Logger logger = LoggerFactory.getLogger(DownloadFileUtil.class);

    /**
     * @Description:文件下载
     *
     * @param filePath  要下载的文件路径
     * @param response
     * @param isOnLine  是否在线下载
     * @throws Exception
     * @throws：异常描述
     * @author:liuhefei
     */
    public static String downLoad(String filePath, HttpServletResponse response, boolean isOnLine)  {
        logger.info("入参：filePath = " + filePath + " , isOnLine = " + isOnLine);
        try{
            File f = new File(filePath);
            if (!f.exists()) {
                response.sendError(404, "File not found!");
                logger.info("下载的文件不存在");
                return "文件不存在";
            }
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
            byte[] buf = new byte[1024];
            int len = 0;

            response.reset(); // 非常重要
            if (isOnLine) { // 在线打开方式
                URL u = new URL("file:///" + filePath);
                response.setContentType(u.openConnection().getContentType());
                response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
                // 文件名应该编码成UTF-8
            } else { // 纯下载方式
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
            }
            OutputStream out = response.getOutputStream();
            while ((len = br.read(buf)) > 0){
                out.write(buf, 0, len);
            }

            br.close();
            out.close();
            return "success";
        }catch (Exception e){
            logger.error("下载文件失败，发生了异常：" + e.getMessage());
            return "文件下载失败";
        }
    }

    /*----------------------------------------------------------------------------*/

    /**
     * @Description:文件下载
     *
     * @param path  欲下载的文件的路径。
     * @param response
     * @return
     * @throws：异常描述
     * @author:liuhefei
     */
    public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            logger.error("下载文件失败，发生了异常：" + ex.getMessage());
            return null;
        }
        return response;
    }

    /**
     * @Description: 文件下载
     *
     * @param filePath  文件的存放路径
     * @param fileName  文件下载后的文件名
     * @param response
     * @throws FileNotFoundException
     * @throws：异常描述
     * @author:liuhefei
     */
    public static void downloadLocal(String filePath,String fileName, HttpServletResponse response) {

        try {
            // 下载本地文件
            //String fileName = "Operator.doc".toString(); // 文件的默认保存名
            // 读到流中
            InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;

            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }

            inStream.close();
        } catch (IOException e) {
            logger.error("下载文件失败，发生了异常：" + e.getMessage());
            return;
        }
    }

    /**
     * 下载本地文件
     * @param path    E:\data\interview\interviewPlans2019102114.zip
     * @param response
     * @return
     */
    public static String downloadFile(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            //如果文件不存在
            if(!file.exists()){

                return "要下载的文件不存在";
            }
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "success";
    }


}
