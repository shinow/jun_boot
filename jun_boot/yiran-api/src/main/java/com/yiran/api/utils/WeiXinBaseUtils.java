package com.yiran.api.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 
 * @类功能说明： 微信对账接口工具类.
 *
 */
public class WeiXinBaseUtils {
	private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 生成16位随机的字符串
	 * 
	 * @return
	 */
	public static String createNoncestr() {
		return createNoncestr(16);
	}

	/**
	 * 生成随机的字符串
	 * 
	 * @param length
	 * @return
	 */
	private static String createNoncestr(int length) {
		StringBuilder sb = new StringBuilder();
		Random rd = new Random();
		int clength = chars.length();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(rd.nextInt(clength - 1)));
		}
		return sb.toString();
	}

	/**
	 * 生成xml文件
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrayToXml(HashMap<String, String> arr) {
		String xml = "<xml>";

		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			if (isNumeric(val)) {
				xml += "<" + key + ">" + val + "</" + key + ">";

			} else
				xml += "<" + key + "><![CDATA[" + val + "]]></" + key + ">";
		}

		xml += "</xml>";
		return xml;
	}

	private static boolean isNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

}
