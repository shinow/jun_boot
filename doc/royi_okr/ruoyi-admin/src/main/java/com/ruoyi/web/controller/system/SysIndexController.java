package com.ruoyi.web.controller.system;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.I18nUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Locale;


/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private I18nUtils i18nUtils;


    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user =  ShiroUtils.getSysUser();

        // 取用户菜单
        List<SysMenu> sysMenus = menuService.selectMenusByUser(user);

        // 根据用户id取出菜单
        List<SysMenu> menus = i18nMenus(menuService.selectMenusByUser(user),true,user.getLanguage());

        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }

    /**
     * 简单处理菜单的翻译
     * @param menus 原始数据
     * @param flag  是否原路返回，主要用来测试数据
     * @param locale  指定需要翻译的语种
     * @return  指定语言的翻译内容
     */
    private  List<SysMenu> i18nMenus(List<SysMenu> menus,Boolean flag,String locale){
        if(!flag)  return menus;
        String   prefix    = "menus.";
        Integer  mensSize  = menus.size();
        if(StringUtils.isEmpty(locale))
        {
            locale = "en_US";
        }
        String[] localeArr = locale.split("_");
        Locale   localeNew = null;
        if( localeArr.length > 1 ){
            localeNew = new Locale(localeArr[0],localeArr[1]);
        } else {
            localeNew = getRequest().getLocale();
        }
        if( mensSize > 0 ){
            for (int i = 0; i < mensSize; i++) {
                SysMenu sysMenu = menus.get(i);
                sysMenu.setMenuName(i18nUtils.getKey(prefix,menus.get(i).getMenuName(),localeNew) );
                if(sysMenu.getChildren().size()>0){
                    i18nMenus(sysMenu.getChildren(),true,locale);
                }
            }
        }
        return menus;
    }
}
