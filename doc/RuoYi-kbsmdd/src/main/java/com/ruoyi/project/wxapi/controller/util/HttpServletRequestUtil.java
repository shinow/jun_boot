package com.ruoyi.project.wxapi.controller.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
	//从request参数中提取取key值，并转化成整型
	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest request, String key) {
		try {
		

			return Long.valueOf(request.getParameter(key));
			
		} catch (Exception e) {
			return -1;
		}
	}
	public static Double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}

	public static boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}
	public static String getString(HttpServletRequest request, String key) {
		try {
			String result = request.getParameter(key);
			if (result != null) {
				result = result.trim();//qud去掉左右两侧的空格
			}
			if ("".equals(result)) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

}
