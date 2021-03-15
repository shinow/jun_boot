package com.mos.eboot.tools.shiro;

import java.util.Set;

import javax.annotation.Resource;

import com.mos.eboot.tools.shiro.entity.IUser;
import com.mos.eboot.tools.shiro.exception.MustPasswordException;
import com.mos.eboot.tools.shiro.exception.MustUsernameException;
import com.mos.eboot.tools.shiro.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * 授权域
 * @author 小尘哥
 */
public abstract class AbstractAuthorizingRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

	@Resource
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		if (primaryPrincipal == null) {
			return null;
		}

		IUser user = (IUser) primaryPrincipal;

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(findPermissions(user));
		info.setRoles(findRoles(user));

		return info;
	}

	/**
	 * 获取用户权限
	 * @param user
	 * @return
	 */
	protected abstract Set<String> findPermissions(IUser user);

	/**
	 * 获取用户角色
	 * @param user
	 * @return
	 */
	protected abstract Set<String> findRoles(IUser user);

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

		IUser user = userService.findUserByUsername(username);
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

	protected AuthenticationInfo assertAuthenticationInfo(IUser user) {
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		return authenticationInfo;
	}

	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
			throws AuthenticationException {
		CredentialsMatcher cm = getCredentialsMatcher();
		if (cm != null) {
			if (!cm.doCredentialsMatch(token, info)) {
				throw new IncorrectCredentialsException();
			}
		} else {
			throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify "
					+ "credentials during authentication.  If you do not wish for credentials to be examined, you "
					+ "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if(permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for(String orPermission : permissions) {
				if(isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if(permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for(String orPermission : permissions) {
				if(!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
		if(permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}
}
