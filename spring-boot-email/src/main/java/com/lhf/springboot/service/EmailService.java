package com.lhf.springboot.service;

/**
 * @ClassName: EmailService
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/29 18:41
 */
public interface EmailService {
    /**
     * 发送简单邮件
     * @param to  收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送网页版邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送正文中有静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param recId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String recId);
}
