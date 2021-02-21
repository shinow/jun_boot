package com.ruoyi.common.utils;

import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.context.MessageSource;
import java.util.Locale;
import static com.ruoyi.common.utils.ServletUtils.getRequest;

/**
 * 获取i18n资源文件
 * 
 * @author ruoyi
 */
public class MessageUtils
{
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args)
    {
        Locale locale = null;
        if(getRequest().getSession(true).getAttribute("localSession") != null ){
            locale = new Locale(getRequest().getSession(true).getAttribute("localSession").toString());
        }else{
            locale = getRequest().getLocale();
        }

        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, locale);
    }
}
