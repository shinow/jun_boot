package com.pearadmin.pro.common.web.exception.auth.token;

/**
 * Describe: TOKEN 验 证 异 常
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class TokenValidationException extends TokenException{

    public TokenValidationException(String message){
        super(message);
    }
}
