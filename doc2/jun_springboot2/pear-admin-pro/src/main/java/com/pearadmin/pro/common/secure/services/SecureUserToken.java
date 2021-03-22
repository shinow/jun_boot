package com.pearadmin.pro.common.secure.services;

import com.pearadmin.pro.common.secure.domain.SecureCache;
import lombok.Data;

@Data
public class SecureUserToken extends SecureCache {

    /**
     * Token Key
     * */
    private String token;

    /**
     * Token 格式化
     * */
    public void setToken(String token){
        this.token = String.format("%s....",token.substring(0,80));
    }
}
