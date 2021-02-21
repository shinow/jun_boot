package com.ruoyi.framework.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.ruoyi.framework.config.ServerModel;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.*;
import java.util.*;

public class DataSourceModel extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSource();
    }

    public DruidDataSource createDataSource(String driverClassName, String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestWhileIdle(true);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(15);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(3000);
        dataSource.setTimeBetweenEvictionRunsMillis(27000);
        dataSource.setMinEvictableIdleTimeMillis(28000);
        dataSource.setLogAbandoned(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(600);
        dataSource.setValidationQuery("SELECT NOW() FROM DUAL");
        return dataSource;
    }

    @Override
    public void setTargetDataSources(Map targetDataSources) {
        // TODO Auto-generated method stub
        Map<Object, Object> _targetDataSources = new HashMap<>();
        Set keySet = targetDataSources.keySet();
        Iterator it = keySet.iterator();
        Object key = "";
        while (it.hasNext()) {
            key = it.next();
        }
        String url = "";
        String password = "root";
        String username = "root";
        //-1表示是日志库，-2为游戏库
        if (String.valueOf(key).equals("-1")) {
            List<ServerModel> serverList = getServerList();
            for (ServerModel server : serverList) {
                try {
                    url = "jdbc:mysql://" + server.getUrl() + "/" + server.getLogDataBase();
                    username = server.getUsername();
                    password = server.getPassword();
                    com.alibaba.druid.pool.DruidDataSource ds = createDataSource("com.mysql.jdbc.Driver", url + "?characterEncoding=utf8&autoReconnect=true", username, password);
                    _targetDataSources.put(String.valueOf(server.getServerId()), ds);
                } catch (Exception e) {
                    logger.error("ServerId:" + server.getServerId() + "初始化日志库连接异常");
                }
            }
            super.setTargetDataSources(_targetDataSources);
        } else if (String.valueOf(key).equals("-2")) {
            List<ServerModel> serverList = getServerList();
            for (ServerModel server : serverList) {
                try {
                    url = "jdbc:mysql://" + server.getUrl() + "/" + server.getGameDataBase();
                    password = server.getPassword();
                    username = server.getUsername();
                    com.alibaba.druid.pool.DruidDataSource ds = createDataSource("com.mysql.jdbc.Driver", url + "?characterEncoding=utf8&autoReconnect=true", username, password);
                    _targetDataSources.put(String.valueOf(server.getServerId()), ds);
                } catch (Exception e) {
                    logger.error("ServerId:" + server.getServerId() + "初始化游戏库连接异常");
                }
            }
            super.setTargetDataSources(_targetDataSources);
        }else{
            super.setTargetDataSources(targetDataSources);
        }
    }

    /**
     * 查询所有区服
     *
     * @return
     */
    public List<ServerModel> getServerList() {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://192.168.1.137:3306/ry?characterEncoding=utf8&amp;autoReconnect=true&amp;failOverReadOnly=false";
        String user = "root";
        String pass = "123456";
        String driver = "com.mysql.jdbc.Driver";
        List<ServerModel> lists = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            sql = "select * from gm_config_server where url!=''";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ServerModel server = new ServerModel();
                server.setServerId(Long.parseLong(rs.getString("server_id")));
                server.setServerName(rs.getString("server_name"));
                server.setUrl(rs.getString("url"));
                server.setGameDataBase(rs.getString("game_data_base"));
                server.setLogDataBase(rs.getString("log_data_base"));
                server.setUsername(rs.getString("username"));
                server.setPassword(rs.getString("password"));
                lists.add(server);
            }
        } catch (SQLException e) {
            System.out.println("数据库操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
        return lists;
    }
}