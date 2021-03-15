package com.ifast.common.utils;

import com.ifast.api.service.AppUserService;
import com.ifast.api.util.JWTUtil;
import com.ifast.sys.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

public class ShiroUtils {

	/**
	 * 兼容jwt和常规开发时获取用户信息
	 * @return
	 */
	public static UserDO getSysUser() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Object principal = subject.getPrincipal();
			if(principal instanceof String){
				String token = (String)principal;
				String userId = JWTUtil.getUserId(token);
				UserDO userDO = SpringContextHolder.getBean(AppUserService.class).selectById(userId);
				return userDO;
			}else if(principal instanceof UserDO) {
				return (UserDO)principal;
			}else if(StringUtils.equals(principal.getClass().toString(), UserDO.class.toString())) {
				UserDO userDO = new UserDO();
				BeanUtils.copyProperties(principal, userDO);
				return userDO;

			}
		}catch (Exception ignore) {
			ignore.printStackTrace();
		}
		return null;
	}
	
	public static Long getUserId() {
		UserDO sysUser = getSysUser();
		return sysUser == null ? null : sysUser.getId();
	}
}
