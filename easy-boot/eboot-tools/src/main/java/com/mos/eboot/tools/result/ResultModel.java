package com.mos.eboot.tools.result;

import com.mos.eboot.tools.util.StringUtil;

import java.io.Serializable;

public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 返回内容
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultModel() {

    }


    public ResultModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultModel(ResultStatus status) {
        this.code = String.valueOf(status.getCode());
        this.msg = status.getMsg();
    }

    public ResultModel(ResultStatus status, T data) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = data;
    }

    public static ResultModel<String> defaultSuccess(String msg) {
        if (StringUtil.isNotBlank(msg)) {
            return new ResultModel<>(ResultStatus.SUCCESS.getCode(), msg);
        } else {
            return new ResultModel<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
        }
    }

    public static ResultModel<String> defaultError(String msg) {
        if (StringUtil.isNotBlank(msg)) {
            return new ResultModel<>(ResultStatus.FAIL.getCode(), msg);
        } else {
            return new ResultModel<>(ResultStatus.FAIL.getCode(), ResultStatus.FAIL.getMsg());
        }
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.isNotEmpty(null));
    }
}
