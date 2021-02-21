/**
 * Copyright 2015-现在 在线教学平台
 */
package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
	// 成功
	SUCCESS(200, "成功"),

	// token异常
	TOKEN_PAST(301, "token过期"), TOKEN_ERROR(302, "token异常"),
	// 登录异常
	LOGIN_ERROR(303, "登录异常"), REMOTE_ERROR(304, "异地登录"),

	// 错误
	ERROR(0, "错误");

	private Integer code;

	private String desc;

}
