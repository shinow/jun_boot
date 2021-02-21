package com.ruoyi.web.controller.demo.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.config.LocaleMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.Locale;


/**
 * 测试多语言的包
 *
 * @author okr
 * @date 2020-05-29
 */
@Controller
@RequestMapping("/local/info")
public class LocalMessageController {
    @Resource
    private LocaleMessage i18n;

    private String prefix = "demo/local";

    @GetMapping()
    public String info()
    {
        System.out.println( "//自定义常量" );
        System.out.println( Constants.LOGIN_FAIL );


        System.out.println( "//框架封装工具类，带参数" );
        System.out.println( MessageUtils.message("user.password.retry.limit.count",2));

        System.out.println("//默认翻译版");
        System.out.println( i18n.getMessage("okr.id") );
        System.out.println( i18n.getMessage("okr.content") );
        System.out.println( i18n.getMessage("login.name") );
        System.out.println( i18n.getMessage("login.title") );
        System.out.println( i18n.getMessage("system.user.title") );
        System.out.println("//指定语种翻译包");
        Locale localeUS = new Locale("en_US");
        System.out.println( i18n.getMessage("okr.id",localeUS) );
        System.out.println( i18n.getMessage("okr.content",localeUS) );

        Locale localeJP =  new Locale("ja_JP");
        System.out.println( i18n.getMessage("okr.id",localeJP) );
        System.out.println( i18n.getMessage("okr.content",localeJP) );

        Locale localeCN =  new Locale("zh_CN");
        System.out.println( i18n.getMessage("okr.id",localeCN) );
        System.out.println( i18n.getMessage("okr.content",localeCN) );


        return prefix + "/info";
    }


}
