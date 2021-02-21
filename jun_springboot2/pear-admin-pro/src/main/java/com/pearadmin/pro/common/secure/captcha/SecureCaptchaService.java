package com.pearadmin.pro.common.secure.captcha;

import com.wf.captcha.SpecCaptcha;
import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.secure.domain.SecureCacheService;
import com.pearadmin.pro.common.web.exception.auth.captcha.CaptchaExpiredException;
import com.pearadmin.pro.common.web.exception.auth.captcha.CaptchaValidationException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * Describe: Captcha 验证码服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SecureCaptchaService {

    /**
     * Redis 封装服务
     * */
    @Resource
    private SecureCacheService<String,SecureCaptcha> secureCacheService;

    /**
     * 创 建 Captcha
     * */
    public SecureCaptcha createCaptcha(){
        SpecCaptcha captcha = new SpecCaptcha(138,40);
        SecureCaptcha secureCaptcha = new SecureCaptcha(UUID.randomUUID().toString(),captcha.text().toLowerCase(),captcha.toBase64());
        return secureCaptcha;
    }

    /**
     * 保存 Captcha
     * */
    public void saveCaptcha(SecureCaptcha secureCaptcha){
        secureCacheService.set(CacheNameConstant.CAPTCHA_NAME_PREFIX + secureCaptcha.getKey(), secureCaptcha, SecurityConstant.CAPTCHA_EXPIRATION);
    }

    /***
     * 创建 Captcha
     * 保存 Captcha
     * */
    public SecureCaptcha createAndSaveCaptcha(){
        SecureCaptcha secureCaptcha = createCaptcha();
        saveCaptcha(secureCaptcha);
        return secureCaptcha;
    }

    /**
     * 验 证 Captcha
     * */
    public void verifyCaptcha(String key, String code) {
        SecureCaptcha captcha = getCaptcha(key);
        if(captcha == null) throw new CaptchaExpiredException("captcha expired");
        if(!captcha.getCode().equals(code)) throw new CaptchaValidationException("captcha invalid");
    }

    /**
     * 获 取 Captcha
     * */
    public SecureCaptcha getCaptcha(String key) {
        return secureCacheService.get(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }

    /**
     * 销 毁 Captcha
     * */
    public void destroyCaptcha(String key){
        secureCacheService.destroy(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }

}
