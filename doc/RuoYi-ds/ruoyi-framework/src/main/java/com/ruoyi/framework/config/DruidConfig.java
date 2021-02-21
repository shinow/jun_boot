package com.ruoyi.framework.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.ruoyi.framework.datasource.DataSourceModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.framework.datasource.DynamicDataSource;

/**
 * druid 配置多数据源
 *
 * @author ruoyi
 */
@Configuration
public class DruidConfig {
//    @Bean(name = "centerDataSource")
//    public DataSourceModel initCenterDataSource() {
//        DataSourceModel data = new DataSourceModel();
//        DruidDataSource ds1 = data.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.137:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true",
//                "root", "123456");
//        DruidDataSource ds2 = data.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.137:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true",
//                "root", "123456");
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceType.MASTER.name(), ds1);
//        targetDataSources.put(DataSourceType.SLAVE.name(), ds2);
//        data.setTargetDataSources(targetDataSources);
//        data.setDefaultTargetDataSource(ds1);
//        return data;
//    }
//
//    @Bean(name = "gameDataSource")
//    public DataSourceModel initDataSource() {
//        DataSourceModel data = new DataSourceModel();
//        DruidDataSource ds1 = data.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.137:3306/online_game_db1100101?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true",
//                "root", "123456");
//        DruidDataSource ds2 = data.createDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.137:3306/online_game_db1100105?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true",
//                "root", "123456");
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("-1", ds1);
//        data.setTargetDataSources(targetDataSources);
//        data.setDefaultTargetDataSource(ds1);
//        return data;
//    }


//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.master")
//    public DataSource masterDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid.slave")
//    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
//    public DataSource slaveDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "dynamicDataSource")
//    @Primary
//    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
//        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
//        return new DynamicDataSource(masterDataSource, targetDataSources);
//    }

}
