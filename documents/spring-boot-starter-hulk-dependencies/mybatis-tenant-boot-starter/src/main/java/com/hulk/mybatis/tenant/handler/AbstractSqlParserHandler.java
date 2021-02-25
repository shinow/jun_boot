
package com.hulk.mybatis.tenant.handler;

import java.util.ArrayList;
import java.util.List;

import com.hulk.mybatis.tenant.parser.ISqlParser;
import com.hulk.mybatis.tenant.parser.ISqlParserFilter;
import com.hulk.mybatis.tenant.parser.SqlInfo;
import com.hulk.mybatis.tenant.parser.SqlParserHelper;
import com.hulk.mybatis.tenant.util.PluginUtils;
import org.apache.ibatis.executor.statement.CallableStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;






import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

/**
 * SQL 解析处理器
 *
 * @author hulk
 * @since 2016-08-31
 */
@Data
@Accessors(chain = true)
public abstract class AbstractSqlParserHandler {

    protected List<ISqlParser> sqlParserList = new ArrayList<>();
    protected ISqlParserFilter sqlParserFilter;

    /**
     * 拦截 SQL 解析执行
     */
    protected void sqlParser(MetaObject metaObject) {
        if (null != metaObject) {
            Object originalObject = metaObject.getOriginalObject();
            StatementHandler statementHandler = PluginUtils.realTarget(originalObject);
            metaObject = SystemMetaObject.forObject(statementHandler);

            if (null != this.sqlParserFilter && this.sqlParserFilter.doFilter(metaObject)) {
                return;
            }

            // @SqlParser(filter = true) 跳过该方法解析
            if (SqlParserHelper.getSqlParserInfo(metaObject)) {
                return;
            }

            // SQL 解析
            if (!CollectionUtils.isEmpty(this.sqlParserList)) {
                // 好像不用判断也行,为了保险起见,还是加上吧.
                statementHandler = metaObject.hasGetter("delegate") ? (StatementHandler) metaObject.getValue("delegate") : statementHandler;
                if (!(statementHandler instanceof CallableStatementHandler)) {
                    // 标记是否修改过 SQL
                    boolean sqlChangedFlag = false;
                    String originalSql = (String) metaObject.getValue(PluginUtils.DELEGATE_BOUNDSQL_SQL);
                    for (ISqlParser sqlParser : this.sqlParserList) {
                        if (sqlParser.doFilter(metaObject, originalSql)) {
                            SqlInfo sqlInfo = sqlParser.parser(metaObject, originalSql);
                            if (null != sqlInfo) {
                                originalSql = sqlInfo.getSql();
                                sqlChangedFlag = true;
                            }
                        }
                    }
                    if (sqlChangedFlag) {
                        metaObject.setValue(PluginUtils.DELEGATE_BOUNDSQL_SQL, originalSql);
                    }
                }
            }
        }
    }
}
