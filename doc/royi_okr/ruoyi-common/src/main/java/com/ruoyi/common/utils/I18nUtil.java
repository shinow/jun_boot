package com.ruoyi.common.utils;

import javax.servlet.http.Cookie;
import java.util.Locale;

public class I18nUtil
{
    /**
     * 根据国家简称 如 cn  => zh_CN
     * @return 语言数组
     */
    public static Locale getLocale (String language)
    {
        if( language.equals("cn") ){
            return new Locale("zh","CN");
        } else if(language.equals("jp") || language.equals("ja") ) {
            return new Locale("ja","JP");
        } else if(language.equals("en") || language.equals("eu")   ) {
            return new Locale("en","US");
        } else {
            return Locale.getDefault();
        }
    }

    /**
     * 获得指定名的Cookie值
     * @param
     */
    public static String getCookie(Cookie[] cookies, String name){
        if( null !=cookies && null != name ) {
            for( Cookie cookie : cookies ) {
                if ( cookie.getName().equals(name) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
