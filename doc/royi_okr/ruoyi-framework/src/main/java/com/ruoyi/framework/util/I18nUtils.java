package com.ruoyi.framework.util;

import java.util.Locale;

import com.ruoyi.framework.config.LocaleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class I18nUtils {
    @Autowired
    private LocaleMessage localeMessage;

    /**
     * 获取key
     * 没有前缀
     * @param key
     * @return
     */
    public  String getKey(String key) {
        return localeMessage.getMessage(key);
    }

    /**
     * 获取key
     * 有前缀
     * @param key
     * @return
     */
    public  String getKey(String prefix,String key) {
        return localeMessage.getMessage(prefix+key,key);
    }

    /**
     * 获取指定哪个配置文件下的key
     * 没有前缀
     * @param key
     * @param local
     * @return
     */
    public  String getKey(String key, Locale local) {
        return localeMessage.getMessage(key, local);
    }

    /**
     * 获取指定哪个配置文件下的key
     * 有前缀
     * @param key
     * @param local
     * @return
     */
    public  String getKey(String prefix,String key, Locale local) {
        return localeMessage.getMessage(prefix+key,key ,local);
    }
}