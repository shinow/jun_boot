package com.hulk.lockd.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author  hulk
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = LockdProperties.PREFIX)
public class LockdProperties {

    public static final String PREFIX = "redisson.lockd";
    //redisson
    private String host;
    private int port;
    private String password;
    private int database = 0;
    private int timeout = 3000;
    private ClusterServer clusterServer;
    private String codec = "org.redisson.codec.JsonJacksonCodec";
    //lock
    //尝试获取锁超时时间 单位：毫秒，默认3秒超时
    private long acquireTimeout = 3000;
    //锁自动过期时间 单位：毫秒，默认10秒自动过期
    private long expireTime = 10000;



    public ClusterServer getClusterServer() {
        return clusterServer;
    }

    public void setClusterServer(ClusterServer clusterServer) {
        this.clusterServer = clusterServer;
    }

    public static class ClusterServer{

        private String[] nodeAddresses ={};

        public String[] getNodeAddresses() {
            return nodeAddresses;
        }

        public void setNodeAddresses(String[] nodeAddresses) {
            this.nodeAddresses = nodeAddresses;
        }
    }
}
