package com.mos.eboot.enumeration;

import com.mos.eboot.tools.util.StringUtil;

/**
 * @author 小尘哥
 * @date 2018/5/2 11:07
 * @description
 */
public enum MenuType {

	MENU("1","菜单"),
	PERMISSION("2","权限");

	MenuType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public static String getName(String code){
		if (StringUtil.isBlank(code)){
			return null;
		}

		for (MenuType menuType : MenuType.values()){
			if (code.equals(menuType.code)){
				return menuType.getName();
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
