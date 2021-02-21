package com.pearframe.common.utils.auth;

import com.alibaba.fastjson.JSON;
import com.pearframe.common.constant.TokenConstant;
import com.pearframe.framework.security.subject.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Describe: Json Web Token 工具类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class TokenUtil {

    /**
     * Describe: 创建 Json Web Token
     * Param: UserDetails
     * Token: Token
     * */
    public static String createAccessToken(CustomUserDetails customUserDetails){

        return Jwts.builder()
                .setId(customUserDetails.getUsername())
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("jmys")
                .claim("authorities", JSON.toJSONString(customUserDetails.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + TokenConstant.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();

    }
}
