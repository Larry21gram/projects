package com.larry.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil implements Runnable{
    private String email; // 收件箱
    private String code ; //激活码


    public EmailUtil(String email, String code) {
        this.email = email;
        this.code = code;
    }

    @Override
    public void run() {

        String from = "2101253376@qq.com";//发件人邮箱
        String host = "smtp.qq.com";
        String key = "gamsvztcaojvefia";


        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host",host); //设置邮件服务器
                properties.setProperty("mail.smtp.auth", "true"); //打开认证

                try {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.ssl.socketFactory", sf);

//            获取默认session对象
                Session session = Session.getDefaultInstance(properties, new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, key);//发件人帐号及授权码
        }
        });

//            创建邮件对象
        MimeMessage message = new MimeMessage(session);
//            设置发件人
        message.setFrom(new InternetAddress(from));
//            设置接受人
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
//            设置邮件主题
        message.setSubject("帐号激活");
//            设置邮件内容
        String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>" +
        "<h3>" +
        "<a href='http://localhost:8080/user?method=change&code="
        + code + "'>http://localhost:8080/user?method=change&code=" + code
        + "</a></h3></body></html>";

        message.setContent(content,"text/html;charset=UTF-8");
//            发送邮件
        Transport.send(message);
        System.out.println("邮件发送成功");

        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        }
