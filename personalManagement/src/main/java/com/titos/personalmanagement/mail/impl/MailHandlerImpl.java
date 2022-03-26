package com.titos.personalmanagement.mail.impl;

import com.titos.info.mailbox.ToEmail;
import com.titos.personalmanagement.config.YkSysConf;
import com.titos.personalmanagement.model.User;
import com.titos.personalmanagement.mail.MailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 发送邮件的实现类
 * @author Titos
 */
@Repository
public class MailHandlerImpl implements MailHandler {
    @Autowired
    private YkSysConf ykSysConf;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public boolean sendAccountVerify(User user, String key) {
        // 获取username和用户的email
        String username = user.getUsername();
        String email = user.getEmail();
        ToEmail toEmail = createPostEmail(new String[]{email}, key, username);
        // 创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mineMessageHelper = new MimeMessageHelper(message, true);
            // 设置发送者
            mineMessageHelper.setFrom(ykSysConf.getMailSender());
            // 设置接收者
            mineMessageHelper.setTo(toEmail.getTos());
            // 邮件主题
            mineMessageHelper.setSubject(toEmail.getSubject());
            // 邮件内容 true表示带有附件或html
            mineMessageHelper.setText(toEmail.getContent(), true);
            try {
                mailSender.send(message);
                return true;
            } catch (MailException e) {
                return false;
            }
        } catch (MessagingException e) {
            throw new RuntimeException("error when preparing to send mail");
        }
    }

    /**
     * 创建发送邮件的对象
     * @param receiver 接收者
     * @param key redis中存储用户的键
     * @param username 用户名
     * @return 发送邮件对象
     */
    private ToEmail createPostEmail(String[] receiver, String key, String username) {
        // 获取ip地址
        String host = ykSysConf.getFrontEndUrl();
        String url = "http://"+host+"/user/verifyEmail/key/username" ;
        // 当前发送邮件的时间
        String sendTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        String subject = "云客系统注册平台验证";
        String content = "<html>\n" +
                "<body>\n" +
                "    <div>亲爱的"+username+"你好!你于"+sendTime+"在云客平台发起了注册，如果是你本人需要点击url来验证是否你的本人</div>\n" +
                "</body>\n" +
                "</html>";
        return new ToEmail(receiver, subject, content);
    }
}
