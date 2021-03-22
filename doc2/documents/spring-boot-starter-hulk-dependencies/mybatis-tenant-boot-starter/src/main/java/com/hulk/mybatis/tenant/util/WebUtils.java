

package com.hulk.mybatis.tenant.util;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Miscellaneous utilities for web applications.
 *
 * @author hulk
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {




	/**
	 * 获取 HttpServletRequest
	 *
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取 HttpServletResponse
	 *
	 * @return {HttpServletResponse}
	 */
	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	/**
	 * 读取cookie
	 *
	 * @param name cookie name
	 * @return cookie value
	 */
	public String getCookieVal(String name) {
		HttpServletRequest request = WebUtils.getRequest();
		Assert.notNull(request, "request from RequestContextHolder is null");
		return getCookieVal(request, name);
	}

	/**
	 * 读取cookie
	 *
	 * @param request HttpServletRequest
	 * @param name    cookie name
	 * @return cookie value
	 */
	public String getCookieVal(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	/**
	 * 清除 某个指定的cookie
	 *
	 * @param response HttpServletResponse
	 * @param key      cookie key
	 */
	public void removeCookie(HttpServletResponse response, String key) {
		setCookie(response, key, null, 0);
	}

	/**
	 * 设置cookie
	 *
	 * @param response        HttpServletResponse
	 * @param name            cookie name
	 * @param value           cookie value
	 * @param maxAgeInSeconds maxage
	 */
	public void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAgeInSeconds);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}



}
