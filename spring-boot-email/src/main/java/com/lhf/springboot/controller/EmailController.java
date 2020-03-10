package com.lhf.springboot.controller;

import com.lhf.springboot.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;

/**
 * @ClassName: EmailController
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 19:08
 */
@Api(value = "邮件服务Api接口")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @ApiOperation("发送简单文本邮件")
    @RequestMapping(value = "/simple", method = RequestMethod.POST)
    public void sendSimpleEmail(@RequestParam(value = "to" ,required = true) @ApiParam("收件人") String to,
                                @RequestParam(value = "subject", required = true) @ApiParam("邮件主题") String subject,
                                @RequestParam(value = "content", required = true) @ApiParam("邮件内容") String content ) throws Exception{
        emailService.sendSimpleMail(to, subject, content);

    }

    @ApiOperation("发送Html邮件")
    @RequestMapping(value = "/html", method = RequestMethod.POST)
    public void sendHtmlEmail(@RequestParam(value = "to" ,required = true) @ApiParam("收件人") String to,
                              @RequestParam(value = "subject" ,required = true) @ApiParam("邮件主题") String subject,
                              @RequestParam(value = "content", required = true) @ApiParam("邮件内容") String content ) throws Exception{
        emailService.sendHtmlMail(to, subject, content);
    }

    @ApiOperation("发送带附件邮件")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public void sendAttachmentsEmail(@RequestParam(value = "to", required = true) @ApiParam("收件人") String to,
                                     @RequestParam(value = "subject", required = true) @ApiParam("邮件主题") String subject,
                                     @RequestParam(value = "content", required = true) @ApiParam("邮件内容") String content,
                                     @RequestParam(value = "filepath", required = true) @ApiParam("附件路径") String filepath){
        emailService.sendAttachmentsMail(to, subject, content, filepath);

    }

    @ApiOperation("发送带静态资源的邮件")
    @RequestMapping(value = "/inline", method = RequestMethod.POST)
    public void sendInlineResourceEmail(@RequestParam(value = "to", required = true) @ApiParam("收件人") String to,
                                        @RequestParam(value = "subject", required = true) @ApiParam("邮件主题") String subject,
                                        @RequestParam(value = "content", required = true) @ApiParam("邮件内容") String content,
                                        @RequestParam(value = "rscPath", required = true) @ApiParam("静态资源路径") String rscPath,
                                        @RequestParam(value = "rscId", required = true) @ApiParam("静态资源Id") String rscId){
        emailService.sendInlineResourceMail(to, subject, content, rscPath, rscId);

    }

    @ApiOperation("模板邮件")
    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public void sendTemplateEmail(@RequestParam(value = "to", required = true) @ApiParam("收件人") String to,
                                  @RequestParam(value = "subject", required = true) @ApiParam("邮件主题") String subject){
        //创建邮件正文
        Context context = new Context();
        Random random = new Random();
        context.setVariable("id", getRandomString(4));
        String emailContent = templateEngine.process("emailTemplate", context);

        emailService.sendHtmlMail(to, subject , emailContent);
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
