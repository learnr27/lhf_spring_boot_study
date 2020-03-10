package com.lhf.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @ClassName: EmailServiceImpl
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 18:41
 */
@Service
public class EmailServiceImpl implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        logger.info("from={}", from);
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        logger.info("to=", to );
        try{
            mailSender.send(mailMessage);
            logger.info("简单邮件发送成功！");
        }catch (Exception e){
            logger.error("邮件发送失败！");
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
            logger.info("html邮件发送成功！");
        }catch (MessagingException e){
            logger.error("邮件发送失败");
        }

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            logger.info("带附件的邮件发送成功！");
        }catch (MessagingException e){
            logger.error("带附件的邮件发送失败！");
        }

    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String recId) {
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(recId, res);

            mailSender.send(message);
            logger.info("带静态资源的邮件发送成功！");
        }catch (MessagingException e){
            logger.error("带静态资源的邮件发送失败！");
        }

    }
}
