package com.pearframe.framework.security.authent;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Describe: 自定义 Security 权限注解实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    /**
     * 实现 @HasPermission 注解
     * */
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {



        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
