package com.mos.eboot.tools.shiro.utils;

import com.mos.eboot.tools.shiro.entity.IUser;
import org.apache.shiro.SecurityUtils;


public class PrincipalUtils {
	
	public static IUser getCurrentUser(){
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if (principal == null) {
			return null;
		}
		return (IUser)principal;
	}

}
