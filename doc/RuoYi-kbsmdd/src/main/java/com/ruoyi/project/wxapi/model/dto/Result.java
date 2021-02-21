/**
 * Copyright 2015-现在 在线教学平台
 */
package com.ruoyi.project.wxapi.model.dto;

import com.ruoyi.project.wxapi.model.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 接口返回对象实体
 * 
 * @author wujing
 */
public final class Result implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(Result.class);

	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private Integer code = ResultEnum.ERROR.getCode();

	/**
	 * 错误信息
	 */
	private String msg = null;

	/**
	 * 返回结果实体
	 */
	private Object data = null;

	public Result() {
	}

	private Result(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static  Result error(String msg) {
		logger.debug("返回错误：code={}, msg={}", ResultEnum.ERROR.getCode(), msg);
		return new Result(ResultEnum.ERROR.getCode(), msg, null);
	}

	public static  Result error(ResultEnum resultEnum) {
		logger.debug("返回错误：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
		return new Result(resultEnum.getCode(), resultEnum.getDesc(), null);
	}

	public static  Result error(int code, String msg) {
		logger.debug("返回错误：code={}, msg={}", code, msg);
		return new Result(code, msg, null);
	}

	public static  Result success(Object data) {
		return new Result(ResultEnum.SUCCESS.getCode(), "", data);
	}
	public static  Result success() {
		return new Result(ResultEnum.SUCCESS.getCode(), "", null);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

}
