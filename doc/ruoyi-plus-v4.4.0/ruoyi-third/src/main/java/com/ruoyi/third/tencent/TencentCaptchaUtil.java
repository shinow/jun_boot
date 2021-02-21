package com.ruoyi.third.tencent;

import com.ruoyi.third.config.ThirdConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TencentCaptchaUtil {


    /**
     * 腾讯验证码验证服务。该方法在包含腾讯验证码的登录或注册方法内调用。
     * @param tencentCaptcha_ticket    使用腾讯验证码的表单内有隐藏域ticket
     * @param tencentCaptcha_Randstr   使用腾讯验证码的表单内有隐藏域 Randstr
     * @param userIp    根据request获取客户端的用户IP
     * @return
     */
    public static String ticketVerify(String tencentCaptcha_ticket,String tencentCaptcha_Randstr,String userIp) {
        try {
            long startt = System.currentTimeMillis();
            String tencentCaptcha_url="https://ssl.captcha.qq.com/ticket/verify?aid=APPID&AppSecretKey=APPSECRETKEY&Ticket=TICKET&Randstr=RAND&UserIP=USERIP";
            String _url =tencentCaptcha_url.replaceAll("APPID", ThirdConfig.getTencentCaptchaAppId()).replaceAll("APPSECRETKEY",ThirdConfig.getTencentCaptchaAppSecretKey()).replaceAll("TICKET",tencentCaptcha_ticket).replaceAll("RAND",tencentCaptcha_Randstr).replaceAll("USERIP",userIp);
            URL url = null;
            HttpURLConnection urlConn = null;
            url = new URL(_url);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rd.close();
            long end = System.currentTimeMillis();
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


}
