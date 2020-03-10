package com.lhf.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName: FileUploadController
 * @Author: liuhefei
 * @Description: 上传文件
 * @Date: 2019/7/25 15:24
 */
@Controller
public class FileUploadController {
    @Value("${filePath}")
    private String filePath;

    @GetMapping("/file")
    public String upload(){
        return "uploading";
    }

    @PostMapping("/uploading")
    public @ResponseBody String uploading(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        try{
            uploadFile(file.getBytes(), filePath, file.getOriginalFilename());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("文件上传失败！");
            return "uploading failure";
        }
        System.out.println("文件上传成功！");
        return "uploading success";
    }

    public void  uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
