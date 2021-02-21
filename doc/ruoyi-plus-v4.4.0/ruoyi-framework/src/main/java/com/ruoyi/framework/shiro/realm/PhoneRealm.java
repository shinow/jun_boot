package com.ruoyi.framework.shiro.realm;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.framework.shiro.token.PhoneToken;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class PhoneRealm extends AuthorizingRealm {

    @Resource
    ISysUserService userService;

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        PhoneToken token = null;

        // 如果是PhoneToken，则强转，获取phone；否则不处理。
        if(authenticationToken instanceof PhoneToken){
            token = (PhoneToken) authenticationToken;
        }else{
            return null;
        }

        String phone = (String) token.getPrincipal();

        SysUser user = userService.selectUserByLoginName(phone);

        if (user == null) {
            user=userService.selectUserByPhoneNumber(phone);
            if (user == null) {
                throw new BusinessException("手机号未注册!");
            }
        }
        return new SimpleAuthenticationInfo(user, phone, this.getName());
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof PhoneToken;
    }
}
