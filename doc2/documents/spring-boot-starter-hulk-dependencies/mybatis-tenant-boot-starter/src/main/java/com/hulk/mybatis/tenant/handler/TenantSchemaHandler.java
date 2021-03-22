
package com.hulk.mybatis.tenant.handler;

/**
 * 租户处理器（ schema 级 ）
 *
 *  @author hulk
 * @since 2017-08-31
 */
public interface TenantSchemaHandler {

    /**
     * 获取 schema 名
     *
     * @return schema 名
     */
    String getTenantSchema();

    /**
     * 根据表名判断是否进行过滤
     *
     * @param tableName 表名
     * @return 是否进行过滤
     */
    boolean doTableFilter(String tableName);
}
