package com.ruoyi.framework.config;

public class ServerModel {
    private String serverIp; //服务器ip
    private String webPort; //服务器web端口
    private String serverName;
    private Long serverId;
    private String url;
    private String gameDataBase;
    private String logDataBase;
    private String username;
    private String password;

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getWebPort() {
        return webPort;
    }

    public void setWebPort(String webPort) {
        this.webPort = webPort;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGameDataBase() {
        return gameDataBase;
    }

    public void setGameDataBase(String gameDataBase) {
        this.gameDataBase = gameDataBase;
    }

    public String getLogDataBase() {
        return logDataBase;
    }

    public void setLogDataBase(String logDataBase) {
        this.logDataBase = logDataBase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}