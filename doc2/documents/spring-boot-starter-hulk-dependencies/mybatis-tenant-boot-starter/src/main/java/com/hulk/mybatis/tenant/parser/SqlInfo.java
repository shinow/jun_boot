
package com.hulk.mybatis.tenant.parser;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Sql Info
 *
 *  @author hulk
 * @since 2017-06-20
 */
@Data
@Accessors(chain = true)
public class SqlInfo {

    /**
     * SQL 内容
     */
    private String sql;
    /**
     * 是否排序
     */
    private boolean orderBy = true;

    public static SqlInfo newInstance() {
        return new SqlInfo();
    }
}
