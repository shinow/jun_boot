package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 服务器信息配置
 */
public class GmConfigServer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String serverId;
    private String serverName;
    private String serverIp;
    private int webPort;
    private int serverPort;
    private int gmPort;
    private String url;
    private String gameDataBase;
    private String logDataBase;
    private String username;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getWebPort() {
        return webPort;
    }

    public void setWebPort(int webPort) {
        this.webPort = webPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getGmPort() {
        return gmPort;
    }

    public void setGmPort(int gmPort) {
        this.gmPort = gmPort;
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
