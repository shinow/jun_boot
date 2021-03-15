package com.mos.eboot.admin.config;

import com.mos.eboot.admin.config.service.IPermissionService;
import com.mos.eboot.admin.config.service.impl.PermissionService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.shiro.AbstractAuthorizingRealm;
import com.mos.eboot.tools.shiro.entity.IUser;
import com.mos.eboot.tools.shiro.exception.MustPasswordException;
import com.mos.eboot.tools.shiro.exception.MustUsernameException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author 小尘哥
 */
public class UserRealm extends AbstractAuthorizingRealm {

    private IPermissionService permissionService;

    public UserRealm() {
    }

    public UserRealm(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    protected Set<String> findPermissions(IUser user) {
		SysUser sysUser = (SysUser) user;
		String userId = SysUser.SUPER_ID.equals(sysUser.getId())? null: sysUser.getId();
        List<String> permissions = permissionService.getPermissions(userId).getData();
        return new HashSet<>(permissions);
    }

    @Override
    protected Set<String> findRoles(IUser user) {
		return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        String username = upt.getUsername();
        char[] password = upt.getPassword();
        if (StringUtils.isBlank(username)) {
            throw new MustUsernameException();
        }

        if (password == null || password.length == 0) {
            throw new MustPasswordException();
        }

        IUser user = getUserService().findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException();
        }

        if (Boolean.TRUE.equals(user.getDisabled())) {
            throw new DisabledAccountException();
        }

        AuthenticationInfo authenticationInfo = assertAuthenticationInfo(user);

        return authenticationInfo;
    }

    @Override
    protected AuthenticationInfo assertAuthenticationInfo(IUser user) {
        if (user == null) {
            return null;
        }
        SysUser sysUser = (SysUser) user;
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                getName());
        return authenticationInfo;
    }

}
