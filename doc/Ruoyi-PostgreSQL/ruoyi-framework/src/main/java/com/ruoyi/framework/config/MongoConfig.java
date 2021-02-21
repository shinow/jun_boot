//package com.ruoyi.framework.config;
//
//import com.mongodb.MongoClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//@Configuration
//public class MongoConfig {
//
//    @Primary
//    @Bean(name = "mongoMasterProperties")
//    @ConfigurationProperties(prefix = "spring.data.mongodb.primary")
//    public MongoProperties mongoMasterProperties() {
//        return new MongoProperties();
//    }
//
//    @Bean(name = "mongoLogProperties")
//    @ConfigurationProperties(prefix = "spring.data.mongodb.secondary")
//    public MongoProperties mongoLogProperties() {
//        return new MongoProperties();
//    }
//
//    @Primary
//    @Bean("mongoTemplate")
//    public MongoTemplate mongoMasterTemplate(@Qualifier("mongoMasterProperties") MongoProperties mongoProperties) {
//        return new MongoTemplate(mongoDbFactory(mongoProperties));
//    }
//
//    @Bean("mongoLogTemplate")
//    public MongoTemplate mongoLogTemplate(@Qualifier("mongoLogProperties") MongoProperties mongoProperties) {
//        return new MongoTemplate(mongoDbFactory(mongoProperties));
//    }
//
//    private MongoDbFactory mongoDbFactory(MongoProperties mongoProperties) {
//        MongoClient mongoClient = new MongoClient(mongoProperties.getHost(),mongoProperties.getPort());
//        return new SimpleMongoDbFactory(mongoClient, mongoProperties.getDatabase());
//    }
//
////    需要用用户名密码的
////    ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());
////    MongoClient mongoClient = new MongoClient(
////            serverAddress,
////            MongoCredential.createScramSha1Credential(mongoProperties.getUsername(),
////                    mongoProperties.getDatabase(), mongoProperties.getPassword()),
////            new MongoClientOptions.Builder().build());
//
//}
