package com.pearframe.common.constant;

/**
 * Describe: Json Web Token 静态配置
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class TokenConstant {

    /**
     *  Access Token 过期时间
     * */
    public static final long EXPIRATION = 1000*60;

    /**
     *  密钥
     * */
    public static final String SECRET = "PEARFRAMESECRET";


    /**
     * Access Token 前缀
     * */
    public static final String TOKEN_PREFIX = "Sans-";


    /**
     * Http Header 请求头
     * */
    public static final String TOKEN_HEADER =  "Authorization";


    /**
     * 不需要认证的地方
     */
    public static final String antMatchers="/index,/login/**,/favicon.ico";
}
