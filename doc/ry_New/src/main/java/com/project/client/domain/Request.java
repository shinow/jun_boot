package com.project.client.domain;

import com.project.common.json.JSON;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private boolean isOK=false;
    private Class messageType;
    private Integer commandType;
    private ReqComm common;
    private Map<String, Object> details = new HashMap<String, Object>();

    public Request()
    {
        this.common = new ReqComm();
    }

    public Map<String, Object> getDetails()
    {
        return this.details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public ReqComm getCommon() {
        return this.common;
    }

    public void setCommon(ReqComm common) {
        this.common = common;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public Class getMessageType() {
        return messageType;
    }

    public void setMessageType(Class messageType) {
        this.messageType = messageType;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        try {
            return JSON.marshal(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "json转换异常："+this;
        }
    }
}
