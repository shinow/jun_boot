package com.mos.eboot.tools.result;

/**
 * 自定义请求状态码
 * @author wangchao
 */
public enum ResultStatus {
    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    SYS_ERROR("500","系统错误");

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String msg;


    ResultStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
