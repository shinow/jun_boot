实现多账号，邮箱轮询发送

#### 使用说明
    

    
    1.添加注解启动多邮件发送
    @EnableMultiMailConfig
    
    2.配置文件
    multi:
        mail:
          protocol: smtp
          hostList:
             - smtp.aliyun.com
             - smtp.aliyun.com
          portList:
             - 465
             - 465
          usernameList:
             - mmserver01@cock.li
             - mmserver02@cock.li
          passwordList:
             - BKj8qmcXnnH3DWlEDegN12EDBN
             - BKj8qmcXnnH3DWlEDegN12EDBN
          properties:
             mail.smtp.auth: true
             mail.smtp.ssl.enable: true
             mail.smtp.socketFactory.class: javax.net.ssl.SSLSocketFactory
             mail.smtp.starttls.enable: true 
             mail.smtp.starttls.required: true
    3.注入
    @Autowired
    private MultiMailSendTemplate multiMailSendTemplate;
    
    4.发送失败会抛出 com.hulk.multimail.exception.MultiMalilException异常

