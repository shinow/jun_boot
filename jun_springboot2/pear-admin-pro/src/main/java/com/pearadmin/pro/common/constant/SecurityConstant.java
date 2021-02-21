package com.pearadmin.pro.common.constant;

/**
 * Describe: Security 静态配置
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class SecurityConstant {

    /**
     * 不需要认证的接口资源
     *
     * TODO 目前不支持正则表达式路径规则
     */
    public static final String HTTP_ACT_MATCHERS="/index,/login,/api/captcha/createCaptcha,/swagger-ui.html";

    /**
     * 不需要认证的静态资源
     * */
    public static final String WEB_ACT_MATCHERS= "/**";

    /**
     * 默认的登录接口
     * */
    public static final String LOGIN_URL = "/login";

    /**
     * 登录接口方式
     * */
    public static final String LOGIN_METHOD = "POST";

    /**
     * 默认的注销接口
     * */
    public static final String LOGOUT_URL = "/logout";

    /**
     * 是否开启验证码
     * */
    public static final Boolean IS_CAPTCHA_VERIFICATION = true;

    /**
     * Captcha 过期时间
     * */
    public static final long CAPTCHA_EXPIRATION = 1000 * 60 * 5;

    /**
     * Token 过期时间
     * */
    public static final long TOKEN_EXPIRATION = 1000 * 60 * 30;

}
