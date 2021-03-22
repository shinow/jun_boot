package com.hulk.multimail.service;


import com.hulk.multimail.config.MultiMailProperties;
import com.hulk.multimail.exception.MultiMalilException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class MultiMailSendTemplate extends JavaMailSenderImpl implements JavaMailSender {
    private MultiMailProperties multiMailProperties;
    private List<String> hostList;
    private List<Integer> portList;
    private List<String> fromList;
    private List<String> usernameList;
    private List<String> passwordList;
    private int size;
    private final static ThreadLocal<Integer> TL = new InheritableThreadLocal<>();
    private static AtomicInteger currentMailId = new AtomicInteger(0);


    public MultiMailSendTemplate(MultiMailProperties multiMailProperties) {
        this.multiMailProperties = multiMailProperties;
        this.hostList = multiMailProperties.getHostList();
        this.portList = multiMailProperties.getPortList();
        this.fromList = multiMailProperties.getFromList();
        this.usernameList = multiMailProperties.getUsernameList();
        this.passwordList = multiMailProperties.getPasswordList();
        this.size = null == usernameList ? 0 : usernameList.size();

    }

    /**
     * 自定义发送
     * @param personal   发送人名字
     * @param subject   邮件主题
     * @param text      邮件内容
     * @param receivers 邮件接收方
     */
    public void customerSend(String personal ,String text, String subject, String[] receivers)  {
        try {
            int id = currentMailId.get();
            TL.set(id);
            String host = hostList.get(id);
            Integer port = portList.get(id);
            String fromname;
            if(fromList != null){
                 fromname = fromList.get(id);
            }else{
                 fromname =  usernameList.get(id);
            }
            String username = usernameList.get(id);
            String password = passwordList.get(id);
            log.info("发送 multimail from:[{}]，username:[{}]，to:[{}], host:[{}], currentMailId:[{}]，subject:[{}]，", fromname, username,receivers, host, id,subject);
            super.setHost(host);
            super.setPort(port);
            super.setUsername(username);
            super.setPassword(password);
            MimeMessage message = createMimeMessage();
            //模拟Outlook发送
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            MimeMessageHelper helper  = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            InternetAddress from = new InternetAddress();
            // 发送人邮箱
            from.setAddress(fromname);
            // 发送人名字
            if(StringUtils.isEmpty(personal)){
                from.setPersonal("personal", StandardCharsets.UTF_8.name());
            }else{
                from.setPersonal(personal, StandardCharsets.UTF_8.name());
            }
            helper.setFrom(from);
            helper.setTo(receivers);
            helper.setSubject(subject);
            helper.setText(text, true);
            // 设置编码和各种参数
            super.setDefaultEncoding(this.multiMailProperties.getDefaultEncoding().name());
            super.setJavaMailProperties(asProperties(this.multiMailProperties.getProperties()));
            super.send(message);
        } catch (Exception e) {
            throw new MultiMalilException(e);
        }finally {
            // 轮询
            currentMailId.set((currentMailId.incrementAndGet()) % size);
            TL.remove();
        }
    }

    /**
     * 自定义发送
     *
     * @param subject   邮件主题
     * @param text      邮件内容
     * @param receivers 邮件接收方
     */

    public void customerSend(String text, String subject, String[] receivers)  {
        this.customerSend(null , text,  subject,  receivers);
    }

    @Override
    protected void doSend(MimeMessage[] mimeMessage, Object[] object) throws MailException {
        super.doSend(mimeMessage, object);
    }

    @Override
    public String getUsername() {
        Integer I = getMailId();
        return this.usernameList.get(I);
    }

    @Override
    public String getPassword() {
        Integer I = getMailId();
        return this.passwordList.get(I);
    }

    @Override
    public String getHost() {
        Integer I = getMailId();
        return this.hostList.get(I);
    }

    @Override
    public int getPort() {
        Integer I = getMailId();
        return this.portList.get(I);
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

    private Integer getMailId() {
        Integer v=  TL.get();
        return v==null? 0:v;
    }
    private static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            currentMailId.set((currentMailId.incrementAndGet()) % 5);
            System.out.println(currentMailId);
        }



    }

}
