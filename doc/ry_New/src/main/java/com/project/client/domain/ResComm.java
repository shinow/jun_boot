package com.project.client.domain;

public class ResComm {
    private Integer commandType;
    private int error_code = 0;
    private String msg = "未知错误";
    private int interval = 60;
    public ResComm(){}
    public ResComm(Integer error_code, String msg){
        this.error_code=error_code;
        this.msg=msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getError_code() {
        return this.error_code;
    }

    public void setError_code(int errorCode) {
        this.error_code = errorCode;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }
}
