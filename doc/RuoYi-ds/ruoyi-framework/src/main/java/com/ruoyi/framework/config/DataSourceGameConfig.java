package com.ruoyi.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.ruoyi.framework.datasource.DataSourceModel;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.ruoyigame.mapper", sqlSessionTemplateRef  = "test2SqlSessionTemplate")
public class DataSourceGameConfig {

    @Bean(name = "gameDataSource")
    public DataSourceModel initDataSource() {
        DataSourceModel data = new DataSourceModel();
        DruidDataSource ds1 = data.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.137:3306/game_db1?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true",
                "root", "123456");
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("-2", ds1);
        data.setTargetDataSources(targetDataSources);
        data.setDefaultTargetDataSource(ds1);
        return data;
    }

    @Bean(name = "test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(value = "gameDataSource")DataSourceModel dataSource) throws Exception {
        SqlSessionFactoryBean bean = new PackagesSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Interceptor[] interceptor = {new PaginationInterceptor()};
        bean.setPlugins(interceptor); //分页插件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath*:game/mapper/*Mapper.xml"));
        bean.setConfigLocation(resolver.getResource("classpath:game/mybatis-config.xml"));
        bean.setTypeAliasesPackage("com.ruoyigame.domain");
        return bean.getObject();
    }

    @Bean(name = "test2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("gameDataSource") DataSourceModel dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
