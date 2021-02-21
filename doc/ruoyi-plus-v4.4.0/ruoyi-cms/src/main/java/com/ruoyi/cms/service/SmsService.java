package com.ruoyi.cms.service;

import com.ruoyi.cms.config.CmsConfig;
import com.ruoyi.cms.domain.Template;
import com.ruoyi.cms.util.email.MailSenderInfo;
import com.ruoyi.cms.util.email.SimpleMailSender;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.third.sms.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsService {

    @Autowired
    private ITemplateService templateService;

    public AjaxResult sendByTemplate(String templateCode, String phone, Map<String,String> params){
        Template template = templateService.selectTemplateByCode(templateCode);
        if(template!=null){

            String content=template.getTemplateContent();

            for(Map.Entry<String, String> a:params.entrySet()){
                String key=a.getKey();
                String value=a.getValue();
                content=content.replaceAll(key,value);
            }

            AjaxResult result=SmsUtil.sendLeYunSms(phone,template.getTemplateName(),content);

            return result;
        }
        return null;
    }
}
