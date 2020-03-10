package com.lhf.springboot.controller;

import net.sourceforge.tess4j.Tesseract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName: FileUploadController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/9/30 11:28
 */
@Controller
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Value("${img.path}")
    private String imgPath;

    @Value("${data.path}")
    private String dataPath;

    @RequestMapping("/")
    public String index(){
        return "upload";
    }

    /**
     * Tesseract 可以和Java的 File 类一起工作，但是不支持表单上传的 MultipartFile 类。为了便于处理，我们添加了一个简单的 convert() 方法，它将 MultipartFile 对象转换成一个普通的 File 对象。
     *
     * 一旦我们利用 Tesseract 提取出了文本，我们只需将该文本和扫描的图像一起添加到模型当中，然后附加到重定向的展示页面 - result。
     * @param file
     * @param redirectAttributes
     * @param model
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView fileUpload(@RequestParam("file")MultipartFile file, @RequestParam("type")Integer type, RedirectAttributes redirectAttributes, Model model){
        logger.info("入参：file = " + file + " , type = " + type);
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imgPath + file.getOriginalFilename());
            //Files.write(path, bytes);
            logger.info("图片地址：" + path.toString());
            logger.info("file = " + file.getOriginalFilename());

            //File convFile = convert(file);

            File imageFile = new File(path.toString());
            if (!imageFile.exists()) {
                logger.error("图片不存在");
                return new RedirectView("error");
            }
            BufferedImage textImage = ImageIO.read(imageFile);

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath(dataPath);  //设置训练库的路径
            if(type == 0){
                tesseract.setLanguage("eng");//英文识别识别
            }else if(type == 1){
                tesseract.setLanguage("chi_sim");//中文识别
            }else {
                return new RedirectView("error");
            }
            //String text = tesseract.doOCR(convFile);
            String text = tesseract.doOCR(textImage);
            logger.info("text = " + text);
            logger.info("file = " + file);
            //redirectAttributes.addFlashAttribute("file", file);
            redirectAttributes.addFlashAttribute("file", path.toString());
            redirectAttributes.addFlashAttribute("text", text);
            return new RedirectView("result");
        }catch (Exception e){
            logger.error("发生了异常，异常信息为："  + e.getMessage());
            return new RedirectView("error");
        }
    }

    @RequestMapping("/result")
    public String result(){
        return "result";
    }

    @RequestMapping("/err")
    public String error(){
        return "error";
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
