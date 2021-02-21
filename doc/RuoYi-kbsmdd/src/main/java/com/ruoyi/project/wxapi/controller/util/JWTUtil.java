package com.ruoyi.project.wxapi.controller.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
    private static String secret = "3ds3zWf2zffa2fhfWTH2dz2sdaAg4fzqA";

    public static void main(String[] args) {
        String token = JWTUtil.generateJWT(1);
        System.out.println(JWTUtil.validate(token));
        System.out.println(JWTUtil.parseJWT(token));
        System.out.println(JWTUtil.validate(token + 3));

    }

    /**
     * 生成token字符串
     * @param userId
     * @return
     */
    public static String generateJWT(Integer userId) {
        String token = JWT.create()
                .withAudience(userId.toString()) //设置接受方信息，一般时登录用户
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    /**
     * 从jwt token字符串中解析userId
     * @param jwtToken
     * @return
     */
    public static Integer parseJWT(String jwtToken) {
        String userId = JWT.decode(jwtToken).getAudience().get(0);
        return Integer.parseInt(userId);
    }

    /**
     * 验证token是否非法
     * @param jwtToken
     * @return
     */
    public static Boolean validate(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(jwtToken);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            return false;
        }
        return true;
    }
}
