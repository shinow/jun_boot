package com.ruoyi.web.controller.system;

import com.ruoyi.common.utils.I18nUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.SysDictData;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/i18n")
public class I18nInfoController{

    @Autowired
    private DictService aictService;

    /**
     * 切换语种
     * @param language 语言
     * @param branch 语言分支
     * @return
     */
    @GetMapping("/switchLanguage")
    @ResponseBody
    public Map<String,Map<String,Object>> switchLanguage(HttpServletRequest request,String language,String branch)
    {
        String planguage       = !StringUtils.isEmpty(language)?language:"en_US";
        String pbranch         = !StringUtils.isEmpty(branch)?branch:"messages";
        String[] languageArr   = planguage.split("_");
        Locale locale          = null;
        if( languageArr.length == 2){
            locale = new Locale(languageArr[0],languageArr[1]);
        } else {
            locale = I18nUtil.getLocale(planguage);
        }
        //设置session有效时长
        request.getSession().setMaxInactiveInterval(365*86400);
        //设置seesion数值
        request.getSession().setAttribute("localSession",locale);
        //获取指定数据包数据
        return getLanguageMap(planguage, pbranch);
    }

    /**
     * 返回当前请求默认语言包
     * @param request 请求携带的基本头部信息
     * @return 返回当前请求默认语言包
     */
    @GetMapping("/getDefault")
    @ResponseBody
    public String getDefaultLocal(HttpServletRequest request,HttpServletResponse response)
    {
        //获取系统的默认区域信息
        String defaultLocal = null;

        if( StringUtils.isNotNull(request.getSession(true).getAttribute("localSession") ) ){
            //从服务端
            defaultLocal = request.getSession(true).getAttribute("localSession").toString();
        } else {
            String localSession = I18nUtil.getCookie(request.getCookies(), "localSession");
            if( null != localSession ) {
                //从客户端 cookie
                defaultLocal = localSession;
            } else {
                //从客户端 浏览器
                defaultLocal = request.getLocale().toString();
            }
        }

        //设置默认语言，写入浏览器
        Cookie cookie = new SimpleCookie("localSession");
        cookie.setValue(defaultLocal);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(31536000);
        cookie.saveTo(request,response);
        return defaultLocal;
    }

    /**
     * 返回当前请求默认语言包
     * @param request 请求携带的基本头部信息
     * @return
     */
    @GetMapping("/getDefaultLanguage")
    @ResponseBody
    public  Map<String,Map<String,Object>>  getDefaultLanguage(HttpServletRequest request,HttpServletResponse response, String branch)
    {
        String defaultLocal = getDefaultLocal(request,response);
        return getLanguageMap(defaultLocal, branch);
    }

    /**
     *
     * 返回后台指定的翻译包文件
     * @param language  “zh_CN”  或者 cn
     * @return
     */
    private Map<String,Object> getLanguage(String language,String branch)
    {
        Locale   locale    = null;
        String   baseName  = "i18n/"+branch;
        String[] langArray = language.replace("_","-").split("-");
        Map<String,Object> keyValueMaps = new HashMap<String, Object>();
        if( langArray.length == 2 ){
            locale = new Locale(langArray[0], langArray[1]);
        } else {
            locale = I18nUtil.getLocale(language);
        }
        ResourceBundle bundle    = ResourceBundle.getBundle(baseName, locale);
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String keyName  = keys.nextElement();
            String keyValue = null;
            try {
                keyValue = new String(bundle.getString(keyName).getBytes("ISO-8859-1"), "UTF8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            keyValueMaps.put(keyName,keyValue);
        }
        return keyValueMaps;
    }

    /**
     * @param language zh_CN
     * @param branch 语言分支
     * @return 对应翻译包分支内容
     */
    private  Map<String,Map<String,Object>> getLanguageMap(String language,String branch)
    {
        Map<String,Map<String,Object>>  languageList  = new HashMap<String, Map<String, Object>>();
        String[] countryArray = language.replace("_","-").split("-");
        String[] languageMaps = getSystemLanguage();
        //数据包获取
        for (int i = 0; i < languageMaps.length; i++) {
            String[] languageMapArray = languageMaps[i].replace("_","-").split("-");
            if(languageMapArray[0].equals(countryArray[0]) ){
                //获得语言包
                languageList.put( languageMapArray[0],getLanguage(language, branch) ) ;
            }else{
                languageList.put( languageMapArray[0],null) ;
            }
        }
        return languageList;
    }

    /**
     *
     * 获得系统提供的语言
     * @return 语言数组
     */
    private String[] getSystemLanguage()
    {
        List<SysDictData> languages = aictService.getType("sys_show_language");
        Integer size                = languages.size();
        String[] retrunLanguages    = new String[size];
        for (int i = 0; i < size; i++) {
            retrunLanguages[i] = languages.get(i).getDictValue();
        }
        return retrunLanguages;
    }

}
