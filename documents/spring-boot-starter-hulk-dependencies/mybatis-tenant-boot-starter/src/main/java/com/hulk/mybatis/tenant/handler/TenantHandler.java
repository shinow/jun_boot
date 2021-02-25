
package com.hulk.mybatis.tenant.handler;

import net.sf.jsqlparser.expression.Expression;

/**
 * 租户处理器（ TenantId 行级 ）
 *
 *  @author hulk
 * @since 2017-08-31
 */
public interface TenantHandler {

    /**
     * 获取租户值
     *
     * @return 租户值
     */
    Expression getTenantId();

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    String getTenantIdColumn();

    /**
     * 根据表名判断是否进行过滤
     *
     * @param tableName 表名
     * @return 是否进行过滤
     */
    boolean doTableFilter(String tableName);
}
