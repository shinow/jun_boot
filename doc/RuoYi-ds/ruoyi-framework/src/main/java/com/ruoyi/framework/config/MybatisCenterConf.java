package com.ruoyi.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.ruoyi.framework.datasource.DataSourceModel;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MybatisCenterConf {
//    @Bean(name = "centerSessionFactory")
//    @Primary
//    public SqlSessionFactory centerSessionFactory(@Qualifier(value = "centerDataSource")DataSourceModel dataSource) throws Exception {
////        typeAliasesPackage: com.ruoyi
////        mapperLocations: classpath*:mapper/**/*Mapper.xml
////        configLocation: classpath:mapper/mybatis-config.xml
//        String mapperLocations = "classpath*:mapper/**/*Mapper.xml";
//        String configLocation = "classpath:mapper/mybatis-config.xml";
//        String typeAliasesPackage = "com.ruoyi";
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource); //数据源
////        sqlSessionFactory.setGlobalConfig(globalConfig); //全局配置
//        Interceptor[] interceptor = {new PaginationInterceptor()};
//        sqlSessionFactory.setPlugins(interceptor); //分页插件
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            //自动扫描Mapping.xml文件
//            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
//            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
//            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//            return sqlSessionFactory.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//    //    MyBatis 动态扫描
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //String basePackage = "com.ruoyi.*.mapper";
//        String basePackage = "com.ruoyi.system.mapper;com.ruoyi.quartz.mapper;com.ruoyi.generator.mapper";
//        mapperScannerConfigurer.setBasePackage(basePackage);
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("centerSessionFactory");
//        return mapperScannerConfigurer;
//    }
//
//    //    配置事务管理
//    @Bean(name = "transactionManager")
//    public DataSourceTransactionManager transactionManager(@Qualifier(value = "centerDataSource")DataSourceModel dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
//    @Bean(name = "gameSessionFactory")
//    public SqlSessionFactory gameSessionFactory(@Qualifier(value = "gameDataSource")DataSourceModel dataSource) throws Exception {
////        typeAliasesPackage: com.ruoyi
////        mapperLocations: classpath*:mapper/**/*Mapper.xml
////        configLocation: classpath:mapper/mybatis-config.xml
//        String mapperLocations = "classpath*:mapper/game/*Mapper.xml";
//        String configLocation = "classpath:mapper/mybatis-config.xml";
//        String typeAliasesPackage = "com.ruoyi2";
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource); //数据源
////        sqlSessionFactory.setGlobalConfig(globalConfig); //全局配置
//        Interceptor[] interceptor = {new PaginationInterceptor()};
//        sqlSessionFactory.setPlugins(interceptor); //分页插件
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            //自动扫描Mapping.xml文件
//            sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
//            sqlSessionFactory.setConfigLocation(resolver.getResource(configLocation));
//            sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//            return sqlSessionFactory.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    @Bean(name = "mapperScannerConfigurer2")
//    public MapperScannerConfigurer mapperScannerConfigurer2() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //String basePackage = "com.ruoyi.*.mapper";
////        String basePackage = "com.ruoyi2.game.mapper";
//        String basePackage = "com.ruoyi2";
//        mapperScannerConfigurer.setBasePackage(basePackage);
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("gameSessionFactory");
//        return mapperScannerConfigurer;
//    }
//
//    @Bean(name = "transactionManager2")
//    public DataSourceTransactionManager transactionManager2(@Qualifier(value = "gameDataSource")DataSourceModel dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
}
