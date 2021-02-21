package com.ruoyi.framework.shiro.authc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName: com.ruoyi.framework.shiro.authc
 * @Description: ****** 重写该类，实现多realm 处理
 * @author: andy-zhangbingbing
 * @version: V1.0
 * @Date: 2020/7/17 12:07
 **/
public class UserModularRealmAuthericator extends ModularRealmAuthenticator {
    private static final Logger logger = LoggerFactory.getLogger(UserModularRealmAuthericator.class);


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //判断getRealms 是否返回为空
        assertRealmsConfigured();
        //强制转换返回自定义的token
        UserLoginToken userLoginToken =  (UserLoginToken) authenticationToken;
        //登录类型
        String loginType = userLoginToken.getLoginType();
        //所有Realm
        Collection<Realm> realms = getRealms();
        //登录类型对应所有的Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for(Realm realm:realms){
            if(realm.getName().contains(loginType)){
                typeRealms.add(realm);
            }
        }
        //判断是单Realm 还是多Realm
        if(typeRealms.size()==1){
            return doSingleRealmAuthentication(typeRealms.iterator().next(),userLoginToken);
        }else{
            return doMultiRealmAuthentication(typeRealms,userLoginToken);
        }
    }
}
