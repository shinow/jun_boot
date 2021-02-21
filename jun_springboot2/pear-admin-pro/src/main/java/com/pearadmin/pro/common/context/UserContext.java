package com.pearadmin.pro.common.context;

import com.pearadmin.pro.modules.sys.domain.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collection;

public class UserContext {

    /**
     * Authentication 认证对象
     * */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * SysUser 当前用户
     * */
    public static SysUser getPrincipal(){ return (SysUser) getAuthentication().getPrincipal(); }

    /**
     * Username 当前用户名
     * */
    public static String getUsername(){ return getPrincipal().getUsername(); }

    /**
     * UserId 用户编号
     * */
    public static String getUserId(){ return getPrincipal().getId(); }

    /**
     * Permission 获取用户权限
     * */
    public static Collection<? extends GrantedAuthority> getAuthority(){
        return getPrincipal().getAuthorities();
    }

}
