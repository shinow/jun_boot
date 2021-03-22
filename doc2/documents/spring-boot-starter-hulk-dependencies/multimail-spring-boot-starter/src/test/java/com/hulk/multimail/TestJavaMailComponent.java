package com.hulk.multimail;


import com.hulk.multimail.service.MultiMailSendTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class TestJavaMailComponent {

    @Autowired
    private MultiMailSendTemplate multiMailSendTemplate;


    /**
     * @param email 邮箱地址
     * @param text  发送内容
     * @return
     * @throws Exception
     */
    public String send(String email, String text) throws Exception {
        MimeMessage message = multiMailSendTemplate.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        InternetAddress from = new InternetAddress();
        from.setAddress(multiMailSendTemplate.getUsername());
        from.setPersonal("BUFF", StandardCharsets.UTF_8.name());
        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("BUFF测试邮件");
        helper.setText(text, true);
        multiMailSendTemplate.send(message);
        return text;
    }

}