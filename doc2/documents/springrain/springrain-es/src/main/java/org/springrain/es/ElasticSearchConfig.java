package org.springrain.es;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;


/**
 * ElasticSearch的配置类
 */
@Configuration("configuration-ElasticSearchConfig")
public class ElasticSearchConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // es的IP和端口,如果是集群,使用逗号隔开,例如 192.168.0.201:9300,192.168.0.202:9300,192.168.0.203:9300
    @Value("${springrain.es.hostport:}")
    private String esHostPort;

    @Bean("restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient restHighLevelClient = null;
        try {
            System.out.println("开始连接elasticsearch...");
            String[] ips = esHostPort.split(",");
            HttpHost[] hosts = new HttpHost[ips.length]; // 定义数组长度
            for (int i = 0; i < ips.length; i++) {
                hosts[i] = new HttpHost(InetAddress.getByName(ips[i].split(":")[0]),
                        Integer.valueOf(ips[i].split(":")[1]), "http");
            }
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(hosts));
            System.out.println("elasticsearch连接成功...");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return restHighLevelClient;

    }

    // ElasticSearch的操作,声明注入,避免出现依赖错误
    @Bean("elasticSearchOperation")
    public ElasticSearchOperation elasticSearchOperation() {
        ElasticSearchOperation elasticSearchOperation = new ElasticSearchOperation();
        elasticSearchOperation.setClient(restHighLevelClient());
        return elasticSearchOperation;
    }


}
