package com.mos.eboot.tools.shiro.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordHelper {

	private static final String DEFAULT_ALGORITHM_NAME = "md5";

	private static final int DEFAULT_HASH_ITERATIONS = 2;

	private String algorithmName = DEFAULT_ALGORITHM_NAME;

	private int hashIterations = DEFAULT_HASH_ITERATIONS;

	public PasswordHelper() {

	}

	public PasswordHelper(String algorithmName, int hashIterations) {
		this.algorithmName = algorithmName;
		this.hashIterations = hashIterations;
	}

	public String encrypt(String password) {
		return new SimpleHash(algorithmName, password).toHex();
	}

	public String encrypt(String password, String salt) {
		return new SimpleHash(algorithmName, password, salt,hashIterations).toHex();
	}

	/**
	 * 验证密码 6-20位，至少含数字/字母/字符2种组合
	 * 特殊字符
	 * _~!@#$%
	 */
//	private static final String PWD_REGEX = "^(?![A-Z\\W_]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9]{6,20}$";
	private static final String PWD_REGEX = "(?!^(\\d+|[a-zA-Z]+|[_~!@#$%^&*?]+)$)^[\\w_~!@#$%\\\\^&*?]{6,20}$";

	public static boolean matchPwd(String password){
		Pattern pattern = Pattern.compile(PWD_REGEX);
		Matcher matcher = pattern.matcher(password);
		boolean rs = matcher.matches();
		return rs;
	}

	public static void main(String[] args) {
		System.out.println(new PasswordHelper().encrypt("123456",null));
		System.out.println(new PasswordHelper().encrypt("123456"));
	}
}
