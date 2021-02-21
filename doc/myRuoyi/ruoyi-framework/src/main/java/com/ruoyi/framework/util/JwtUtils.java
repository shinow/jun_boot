package com.ruoyi.framework.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ruoyi.common.utils.DateUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    private static final String SECRET = "org.qiqiang.secret";
    private static final String ISSUER = "ruoyi";
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    public static void main(String[] ss) throws Exception {
        /*String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJhZG1pblwiOnRydWUsXCJhdmF0YXJcIjpcImY2OWRkZmU3YTQwMWE4ZWY2OGZjNzE3MWY2MDI3ZDg5LnBuZ1wiLFwiY3JlYXRlVGltZVwiOjE1MjExNzExODAwMDAsXCJkZWxGbGFnXCI6XCIwXCIsXCJkZXB0XCI6e1wiZGVwdElkXCI6MTAzLFwiZGVwdE5hbWVcIjpcIueglOWPkemDqOmXqFwiLFwib3JkZXJOdW1cIjpcIjFcIixcInBhcmFtc1wiOnt9LFwicGFyZW50SWRcIjoxMDEsXCJzdGF0dXNcIjpcIjBcIn0sXCJkZXB0SWRcIjoxMDMsXCJlbWFpbFwiOlwicnlAMTYzLmNvbVwiLFwibG9naW5EYXRlXCI6MTU0NTYzMjgyMTc2MCxcImxvZ2luSXBcIjpcIjEyNy4wLjAuMVwiLFwibG9naW5OYW1lXCI6XCJhZG1pblwiLFwicGFyYW1zXCI6e30sXCJwYXNzd29yZFwiOlwiMjljNjdhMzAzOTg2MzgyNjlmZTYwMGY3M2EwNTQ5MzRcIixcInBob25lbnVtYmVyXCI6XCIxNTg4ODg4ODg4OFwiLFwicmVtYXJrXCI6XCLnrqHnkIblkZhcIixcInJvbGVzXCI6W3tcImRhdGFTY29wZVwiOlwiMVwiLFwiZmxhZ1wiOmZhbHNlLFwicGFyYW1zXCI6e30sXCJyb2xlSWRcIjoxLFwicm9sZUtleVwiOlwiYWRtaW5cIixcInJvbGVOYW1lXCI6XCLnrqHnkIblkZhcIixcInJvbGVTb3J0XCI6XCIxXCIsXCJzdGF0dXNcIjpcIjBcIn1dLFwic2FsdFwiOlwiMTExMTExXCIsXCJzZXhcIjpcIjFcIixcInN0YXR1c1wiOlwiMFwiLFwidXNlcklkXCI6MSxcInVzZXJOYW1lXCI6XCLoi6Xkvp1cIn0iLCJpc3MiOiJydW95aSIsImV4cCI6MTU0NTcxOTIyMX0.-YJJ7mMN3OoERsfM7Daa0X9Poj93ceulVRABTbqYBAg";
        String verifyToken = verifyToken(token);
        System.out.println(verifyToken);
        SysUser sysUser = JSON.parseObject(verifyToken, SysUser.class);
        System.out.println(sysUser.getRoles().get(0).getRoleName());*/
        String token = createToken("1", 6);
        System.out.println(token);

    }
    /**
     * 生成token
     *
     * @param infoJson token包含的信息,建议转成Json字符串存入
     * @param second token有效时长(秒)
     * @return 返回生成的token字符串
     */
    public static String createToken(String infoJson, int second) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    .withExpiresAt(DateUtils.addSeconds(new Date(), second)).withSubject(infoJson);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            logger.error("生成token失败",e);
            throw new Exception("生成token失败");
        }
    }

    /**
     * 验证jwt，并返回数据
     * @param token
     * @return 失败返回null
     * @throws Exception
     */
    public static String verifyToken(String token) throws Exception {
        Algorithm algorithm;
        String re = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            re = jwt.getSubject();
        } catch (Exception e) {
            logger.info("鉴权失败",e);
        }
        return re;
    }
    /**
     * 获得token的到期时间
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getExpiresAt();
        } catch (Exception e) {
            logger.error("获取有效时间失败",e);
            return null;
        }
    }
    /**
     * 获得token的到期剩余时间 毫秒
     * @return token中包含的签发时间
     */
    public static long getLiveTime(String token) {
        try {
            Date date = getIssuedAt(token);
            if (date!=null){
                return (date.getTime()-new Date().getTime());
            }
        } catch (Exception e) {
            logger.error("获取剩余有效时间失败",e);
            return 0;
        }
        return 0;
    }


    /**
     * token是否过期
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        Date issuedAt = getIssuedAt(token);
        return issuedAt==null?true:issuedAt.before(now);
    }

    /**
     * 生成随机盐,长度32位
     * @return
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(16).toHex();
        return hex;
    }

}
