package com.hulk.excel.handler;

import com.hulk.excel.annotation.RespExcel;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hulk
 * @date 2020/3/29
 * <p>
 * sheet 写出处理器
 */
public interface SheetWriteHandler {

	/**
	 * 是否支持
	 *
	 * @param obj
	 * @return
	 */
	boolean support(Object obj);

	/**
	 * 校验
	 *
	 * @param respExcel 注解
	 */
	void check(RespExcel respExcel);

	/**
	 * 返回的对象
	 *
	 * @param o             obj
	 * @param response      输出对象
	 * @param respExcel 注解
	 */
	void export(Object o, HttpServletResponse response, RespExcel respExcel);

	/**
	 * 写成对象
	 *
	 * @param o             obj
	 * @param response      输出对象
	 * @param respExcel 注解
	 */
	void write(Object o, HttpServletResponse response, RespExcel respExcel);
}
