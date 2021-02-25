
package com.hulk.mybatis.tenant.parser;


import com.hulk.mybatis.tenant.annotation.SqlParser;
import com.hulk.mybatis.tenant.util.StringPool;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SQL 解析辅助类
 *
 * @author hulk
 * @since 2018-07-22
 */
public class SqlParserHelper {

    public static final String DELEGATE_MAPPED_STATEMENT = "delegate.mappedStatement";
    /**
     * SQL 解析缓存
     * key 可能是 mappedStatement 的 ID,也可能是 class 的 name
     */
    private static final Map<String, Boolean> SQL_PARSER_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 初始化缓存 接口上 SqlParser 注解信息
     *
     * @param mapperClass Mapper Class
     */
    @Deprecated
    public synchronized static void initSqlParserInfoCache(Class<?> mapperClass) {
        SqlParser sqlParser = mapperClass.getAnnotation(SqlParser.class);
        if (sqlParser != null) {
            SQL_PARSER_INFO_CACHE.put(mapperClass.getName(), sqlParser.filter());
        }
    }

    /**
     * 初始化缓存 方法上 SqlParser 注解信息
     *
     * @param mapperClassName Mapper Class Name
     * @param method          Method
     */
    @Deprecated
    public static void initSqlParserInfoCache(String mapperClassName, Method method) {
        SqlParser sqlParser = method.getAnnotation(SqlParser.class);
        if (sqlParser != null) {
            if (SQL_PARSER_INFO_CACHE.containsKey(mapperClassName)) {
                // mapper 接口上有注解
                Boolean value = SQL_PARSER_INFO_CACHE.get(mapperClassName);
                if (!value.equals(sqlParser.filter())) {
                    // 取反,不属于重复注解,放入缓存
                    String sid = mapperClassName + StringPool.DOT + method.getName();
                    SQL_PARSER_INFO_CACHE.putIfAbsent(sid, sqlParser.filter());
                }
            } else {
                String sid = mapperClassName + StringPool.DOT + method.getName();
                SQL_PARSER_INFO_CACHE.putIfAbsent(sid, sqlParser.filter());
            }
        }
    }

    /**
     * 获取 SqlParser 注解信息
     *
     * @param metaObject 元数据对象
     */
    public static boolean getSqlParserInfo(MetaObject metaObject) {
        String id = getMappedStatement(metaObject).getId();
        Boolean value = SQL_PARSER_INFO_CACHE.get(id);
        if (value != null) {
            return value;
        }
        String mapperName = id.substring(0, id.lastIndexOf(StringPool.DOT));
        return SQL_PARSER_INFO_CACHE.getOrDefault(mapperName, false);
    }

    /**
     * 获取当前执行 MappedStatement
     *
     * @param metaObject 元对象
     */
    public static MappedStatement getMappedStatement(MetaObject metaObject) {
        return (MappedStatement) metaObject.getValue(DELEGATE_MAPPED_STATEMENT);
    }
}
