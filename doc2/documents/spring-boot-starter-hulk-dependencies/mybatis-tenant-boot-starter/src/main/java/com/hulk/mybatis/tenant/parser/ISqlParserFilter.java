
package com.hulk.mybatis.tenant.parser;

import org.apache.ibatis.reflection.MetaObject;

/**
 * SQL 解析过滤器
 *
 *  @author hulk
 * @since 2017-09-02
 */
public interface ISqlParserFilter {

    boolean doFilter(MetaObject metaObject);

}
