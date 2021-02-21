//package com.ruoyi.framework.config;
//
//import com.mongodb.MongoClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//@Configuration
//@ConfigurationProperties(prefix = "spring.data.mongodb.primary")
//public class PrimaryMongoConfig {
//    /**
//     * ip与域名
//     */
//    @Value("${spring.data.mongodb.primary.host}")
//    private String host;
//    /**
//     * 数据库
//     */
//    @Value("${spring.data.mongodb.primary.database}")
//    private String database;
//    /**
//     * 端口
//     */
//    @Value("${spring.data.mongodb.primary.port}")
//    private int port;
//
//    /**
//     * 用户名
//     */
//    @Value("${spring.data.mongodb.primary.username}")
//    private String username;
//
//    /**
//     *  密码
//     */
//    @Value("${spring.data.mongodb.primary.password}")
//    private String password;
//
//    public MongoDbFactory mongoDbFactory() throws  Exception{
//        return new SimpleMongoDbFactory(new MongoClient(host,port),database);
//    }
//    @Primary
//    @Bean(name = "mongoTemplatePrimary")
//    public MongoTemplate getMongoTemplate() throws  Exception{
//        return new MongoTemplate(mongoDbFactory());
//    }
//
//}
