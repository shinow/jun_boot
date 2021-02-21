package com.ruoyi.framework.config;


import com.ruoyi.common.utils.I18nUtil;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class MyLocaleResolver implements LocaleResolver
{

    @Value("${shiro.cookie.languageName}")
    private String languageName;

    @Value("${shiro.cookie.languageTime}")
    private int languageTime;

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest)
    {
        String language = httpServletRequest.getParameter("language");
        if (StringUtils.isEmpty(language)){
            if(ShiroUtils.getSysUser() != null){
                String userLanguage    =  ShiroUtils.getSysUser().getLanguage();
                if(!StringUtils.isEmpty( userLanguage )){
                    String[] userLanguageArr = splitLanguageWith(userLanguage,"_");
                    if(userLanguageArr.length == 2){
                        //更新服务端 Session
                        httpServletRequest.getSession().setAttribute("localSession",userLanguage);
                        //更新客户端 Cookie
                        HttpServletResponse httpServletResponse = ServletUtils.getResponse();
                        Cookie cookie = new SimpleCookie( languageName );
                        cookie.setValue(  userLanguage  );
                        cookie.setHttpOnly(false);
                        cookie.setMaxAge(languageTime * 24 * 60 * 60);
                        cookie.saveTo (httpServletRequest,httpServletResponse);
                        return new Locale(userLanguageArr[0],userLanguageArr[1]);
                    }
                }
            }
        } else {
            String[] urlLanguageArr = splitLanguageWith(language,"_");
            if(urlLanguageArr.length == 2 ){
                return new Locale(urlLanguageArr[0],urlLanguageArr[1]);
            }else{
                return  I18nUtil.getLocale(language);
            }
        }
        return Locale.getDefault();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response,Locale locale)
    {
        //设置默认语言，写入浏览器
        Cookie cookie = new SimpleCookie( languageName );
        cookie.setValue(  locale.toString() );
        cookie.setHttpOnly(false);
        cookie.setMaxAge(languageTime * 24 * 60 * 60);
        cookie.saveTo (request,response);
    }

    /**
     * switch language to array
     * @param language
     * @param Separator
     * @return
     */
    private String[] splitLanguageWith(String language,String Separator)
    {
       return  language.split(Separator);
    }

}
