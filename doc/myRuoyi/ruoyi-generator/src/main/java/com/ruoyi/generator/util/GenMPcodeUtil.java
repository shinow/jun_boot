package com.ruoyi.generator.util;


import cn.hutool.db.DbUtil;
import cn.hutool.db.Session;
import cn.hutool.db.handler.RsHandler;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.generator.domain.ColumnInfo;
import com.ruoyi.generator.domain.TableInfo;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * mybaties-plus代码生成
 * @author pmc
 * @since 2019年1月4日
 */
public class GenMPcodeUtil {
    private static final String dburl = "jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    public static void main(String[] args) {
        generateCode(new String[]{"sys_test"});
    }

    /**
     * @param tableNames 要生成的表
     */
    public static void generateCode(String... tableNames) {
        String packageName = "com.ruoyi";
        String modelName = "business";
        generateByTables(packageName, modelName, tableNames);
    }

    /**
     * @param packageName 项目主包名
     * @param modelName 模块包名
     * @param tableNames 数据库表名们
     */
    private static void generateByTables(String packageName, String modelName, String... tableNames) {
        new AutoGenerator()
                /*数据源配置*/
                .setDataSource(
                        new DataSourceConfig()
                                .setDbType(DbType.MYSQL)
                                .setUrl(dburl)
                                .setUsername("root")
                                .setPassword("")
                                .setDriverName("com.mysql.cj.jdbc.Driver")
                                .setTypeConvert(new MySqlTypeConvert() {
                                    // 自定义数据库表字段类型转换【可选】
                                    @Override
                                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                                        System.out.println("转换类型：" + fieldType);
                                        if (fieldType.toLowerCase().contains("bigint")) {
                                            //这里目的是把主键转成字符串类型
                                            return DbColumnType.STRING;
                                        }
                                        return super.processTypeConvert(globalConfig, fieldType);
                                    }
                                })
                )
                /*代码生成策略配置*/
                .setGlobalConfig(
                        new GlobalConfig().setActiveRecord(false)//开启 ActiveRecord 模式
                                .setAuthor("codeGen")
                                .setOutputDir("d:\\codeGen\\MP")
                                .setFileOverride(true)
                                .setServiceName("%sServiceI")
                )
                .setStrategy(
                        new StrategyConfig()
                                .setCapitalMode(true)
                                .setEntityLombokModel(false)//是否采用Lombok插件
                                .setNaming(NamingStrategy.underline_to_camel)//驼峰
                                .setInclude(tableNames)//修改替换成你需要的表名，多个表名传数组
                )
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("web.controller")//controller包名
                                .setEntity(modelName + ".bean")//实体类包名 以下类推
                                .setMapper(modelName + ".mapper")
                                .setXml(modelName + ".mapper.xml")
                                .setService(modelName + ".service")
                                .setServiceImpl(modelName + ".service.impl")
                ).setTemplate(
                        new TemplateConfig()
                            .setController(null)//不生成controller
                )
                .execute();
                /**myRuoyi模板*/
        try {
            new FileOutputStream(FileUtils.file("d:\\codeGen\\RY.zip")).write(generatorCode(tableNames));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ruoyi代码生成
     * @param tableNames
     * @return
     */
    private static byte[] generatorCode(String... tableNames) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames)
        {
            // 查询表信息
            TableInfo table = null;
            List<ColumnInfo> columns = null;
            try {
                Session session = DbUtil.newSession();
                String sql = "select table_name, table_comment, create_time, update_time from information_schema.tables where table_comment <> '' and table_schema = (select database()) and table_name = ?";
                table = session.query(sql, new RsHandler<TableInfo>() {
                    @Override
                    public TableInfo handle(ResultSet rs) throws SQLException {
                        TableInfo tableInfo = new TableInfo();
                        while (rs.next()){
                            tableInfo.setTableName(rs.getString("table_name"));
                            tableInfo.setTableComment(rs.getString("table_comment"));
                            tableInfo.setCreateTime(rs.getDate("create_time"));
                            tableInfo.setUpdateTime(rs.getDate("update_time"));
                        }
                        return tableInfo;
                    }
                }, tableName);
                String sql2 = "select column_name, data_type, column_comment from information_schema.columns" +
                        " where table_name = ? and table_schema = (select database()) order by ordinal_position";
                columns = session.query(sql2,ColumnInfo.class,tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 查询列信息
            // 生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
