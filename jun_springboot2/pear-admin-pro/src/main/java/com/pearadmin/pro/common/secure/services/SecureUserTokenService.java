package com.pearadmin.pro.common.secure.services;

import java.util.*;
import com.pearadmin.pro.common.secure.domain.SecureCacheService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.constant.TokenConstant;
import com.pearadmin.pro.common.web.exception.auth.token.TokenException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenExpiredException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenValidationException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Describe: Security User Token 操作服务
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class SecureUserTokenService {

    /**
     * 安 全 缓 存 服 务
     * */
    @Resource
    private SecureCacheService<String, SecureUser> secureCacheService;

    /**
     * 创建 Token
     * */
    public String createToken(SecureUser secureUser){
        return Jwts.builder().setId(secureUser.getId())
                .setSubject(secureUser.getUsername())
                .setIssuedAt(new Date()).setIssuer(TokenConstant.ISSUER)
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();
    }

    /**
     * 保存 Token
     * */
    public void saveToken(String token, SecureUser secureUser){
        secureCacheService.set(CacheNameConstant.TOKEN_NAME_PREFIX + token, secureUser,  SecurityConstant.TOKEN_EXPIRATION);
    }

    /**
     * 创建 Token
     * 保存 Token
     * */
    public String createAndSaveToken(SecureUser secureUser){
        String token = createToken(secureUser);
        saveToken(token,secureUser);
        return token;
    }

    /**
     * 验证 Token
     * */
    public SecureUser verifyToken(String token) throws TokenException{
        parseToken(token);
        SecureUser customUserDetails = getToken(token);
        if(customUserDetails == null) throw new TokenExpiredException("token expired");
        return customUserDetails;
    }

    /**
     * 解析 Token
     * */
    public void parseToken(String token) {
        try {
            Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody();
        }catch (Exception e){ throw new TokenValidationException("token validation failure"); }
    }

    /**
     * 获取 Token Value
     * */
    public SecureUser getToken(String token){
        return secureCacheService.get(CacheNameConstant.TOKEN_NAME_PREFIX + token);
    }

    /**
     * 销毁 Token
     * */
    public void destroyToken(String token){
        secureCacheService.destroy(CacheNameConstant.TOKEN_NAME_PREFIX + token);
    }

    /**
     * 查询 Token Value
     * */
    public List<SecureUser> selectUser(){
        Set<String> keys= secureCacheService.scanKey(CacheNameConstant.TOKEN_NAME_PREFIX + "*");
        List<SecureUser> secureUsers = new ArrayList<>();

        keys.forEach(key -> {
            secureUsers.add(secureCacheService.get(key));
        });
        return secureUsers;
    }
}
