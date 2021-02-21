package com.ruoyi.controller;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * Shiro中的Authentication，主要是检验token时使用
 */
public class AesToken implements AuthenticationToken{
    String token;
    public AesToken(String token){
        this.token=token;
    }
    public Object getPrincipal() {
        return token;
    }

    public Object getCredentials() {
        return token;
    }

}
