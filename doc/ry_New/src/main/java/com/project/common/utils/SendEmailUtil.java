package com.project.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class SendEmailUtil {
    public static void main(String[] args) {
        // 腾讯个人邮箱，密码需要使用授权码
        // MailFromUserModel fromUser = new MailFromUserModel(
        // "smtp.qq.com",
        // "1228759176@qq.com",
        // "个人授权码");//需登录个人邮箱获取
        // 腾讯企业邮箱可直接使用登录账号和密码
        MailFromUserModel fromUser = new MailFromUserModel(
                "smtp.exmail.qq.com",
                "liws@yuanv.com",
                "企业邮箱登录密码");
        MailToUserModel toUser = new MailToUserModel(
                "1228759176@qq.com",
                "这是标题",
                "这是内容",
                new File("D:/1.txt"),
                "附件名称.txt");
        System.out.println(sendSMTPEmail(fromUser, toUser));
    }


    /**
     * 使用smtp邮件协议发送邮件
     *
     * @param fromUser
     * @param toUser
     * @return
     */
    public static SendEmailResult sendSMTPEmail(MailFromUserModel fromUser, MailToUserModel toUser) {
        try {
            // 1.设置邮件session的属性配置
            Properties properties = new Properties();
            properties.setProperty("mail.debug", "true");// 开启debug调试，以便在控制台查看
            properties.setProperty("mail.smtp.auth", "true");// 发送服务器需要身份验证
            properties.setProperty("mail.transport.protocol", "smtp");// 发送邮件协议名称
            // 开启SSL加密，否则会失败
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            // 2.创建session
            Session session = Session.getInstance(properties);
            Transport ts = session.getTransport();
            ts.connect(fromUser.getHost(), fromUser.getUser(), fromUser.getPassWord());

            // 3.创建邮件对象
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromUser.getUser()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser.getUser()));
            // 3.1设置邮件标题
            message.setSubject(toUser.getSubject());
            // 3.2设置邮件主体
            Multipart multipart = new MimeMultipart();
            // 3.3设置邮件消息内容
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(toUser.getText());
            multipart.addBodyPart(messageBodyPart);
            // 3.4邮件主体设置附件
            if (toUser.getFile() != null) {
                if(!toUser.getFile().exists()){
                    return new SendEmailResult(-1, "邮件主体中的附件不存在:" + toUser.getFile().getAbsolutePath());
                }
                messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(new FileDataSource(toUser.getFile())));
                messageBodyPart.setFileName(MimeUtility.encodeText(toUser.getFileName()));
                multipart.addBodyPart(messageBodyPart);
            }
            // 3.5设置邮件主体内容
            message.setContent(multipart);
            // 4.发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();//若系统中邮件发送较多，该对象可静态化不再关闭

            return new SendEmailResult(0, "邮件发送成功。");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return new SendEmailResult(-1, "发送邮件开启SSL加密时异常。");
        } catch (MessagingException e) {
            e.printStackTrace();
            return new SendEmailResult(-1, "发送邮件的内容异常。");
        } catch (Exception e) {
            e.printStackTrace();
            return new SendEmailResult(-1, "发送邮件未知异常。");
        }

    }

    /**
     * 发送邮件返回的对象
     */
    public static class SendEmailResult {
        private Integer code;
        private String msg;
        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
        public SendEmailResult(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        @Override
        public String toString() {
            return "SendEmailResult{" + "code=" + code + ", msg='" + msg + '\'' + '}';
        }
    }

    /**
     * 发送人邮件对象
     */
    public static class MailFromUserModel {
        private String host;
        private String user;
        private String passWord;
        public String getHost() {
            return host;
        }
        public String getUser() {
            return user;
        }
        public String getPassWord() {
            return passWord;
        }
        public MailFromUserModel(String host, String user, String passWord) {
            this.host = host;
            this.user = user;
            this.passWord = passWord;
        }
    }

    /**
     * 接收邮件对象
     */
    public static class MailToUserModel {
        private String user;
        private String subject;
        private String text;
        private File file;
        private String fileName;
        public String getUser() {
            return user;
        }
        public String getSubject() {
            return subject;
        }
        public String getText() {
            return text;
        }
        public File getFile() {
            return file;
        }
        public String getFileName() {
            return fileName;
        }
        public MailToUserModel(String user, String subject, String text, File file, String fileName) {
            super();
            this.user = user;
            this.subject = subject;
            this.text = text;
            this.file = file;
            this.fileName = fileName;
        }
    }
}
