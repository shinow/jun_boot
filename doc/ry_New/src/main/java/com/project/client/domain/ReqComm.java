package com.project.client.domain;

public class ReqComm {
    private String usercode;
    private String token;
    private String machine_code;
    private String client_version;
    private String client_ip;
    private String roleid;
    private int clienttype = 1;

    public String getMachine_code() { return this.machine_code; }

    public void setMachine_code(String machineCode) {
        this.machine_code = machineCode;
    }
    public String getClient_version() {
        return this.client_version;
    }
    public void setClient_version(String clientVersion) {
        this.client_version = clientVersion;
    }

    public String getClient_ip()
    {
        return this.client_ip;
    }
    public void setClient_ip(String clientIp) {
        this.client_ip = clientIp;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUsercode() {
        return this.usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getRoleid() {
        return this.roleid;
    }
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    public int getClienttype() {
        return this.clienttype;
    }
    public void setClienttype(int clienttype) {
        this.clienttype = clienttype;
    }
}
