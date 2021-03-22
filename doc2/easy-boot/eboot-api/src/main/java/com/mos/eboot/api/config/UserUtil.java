package com.mos.eboot.api.config;

import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.util.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 小尘哥
 * @date 2018/5/7 11:48
 * @description
 */
public class UserUtil {

	private static final String ANONYMOUS_USER = "anonymousUser";

	public static SysUser getCurrentUser(){
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj == null || StringUtils.isBlank(obj.toString()) || ANONYMOUS_USER.equals(obj)) {
			return null;
		}
		return FastJsonUtils.toBean(obj.toString(), SysUser.class);
	}
}
