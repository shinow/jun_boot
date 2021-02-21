package com.project.client.domain;

import com.project.common.json.JSON;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public Response(Integer commandType){
        this.common.setCommandType(commandType);
    }

    private ResComm common = new ResComm();

    private Map<String, Object> details = new HashMap<>();

    public ResComm getCommon() {
        return this.common;
    }

    public Response setCommon(ResComm common) {
        this.common.setMsg(common.getMsg());
        this.common.setError_code(common.getError_code());
        this.common.setInterval(common.getInterval());
        return this;
    }

    public Map<String, Object> getDetails() {
        return this.details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }

    public String writeBackStr(){
        String res = "";
        try {
            String str = JSON.marshal(this);
            res = lenString(str.getBytes("UTF-8").length)+str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static Response response(Integer commandType, Integer code,String msg){

        return new Response(commandType).setCommon(new ResComm(code,msg));
    }
    private String lenString(int len){
        String ret = "";
        if (String.valueOf(len).length() > 8) {
            ret = String.valueOf(len);
        } else {
            int b = String.valueOf(len).length();
            System.out.println(String.valueOf(len).length());
            for (int i = b; i < 8; i++) {
                ret = ret + "0";
            }
            ret = ret + String.valueOf(len);
        }
        return ret;
    }
}
