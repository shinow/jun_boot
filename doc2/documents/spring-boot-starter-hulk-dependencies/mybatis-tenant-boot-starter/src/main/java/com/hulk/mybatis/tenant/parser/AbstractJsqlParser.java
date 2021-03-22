
package com.hulk.mybatis.tenant.parser;

import com.hulk.mybatis.tenant.exception.MybatisTenantException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 抽象 SQL 解析类
 *
 *  @author hulk
 * @since 2017-06-20
 */
@Slf4j
public abstract class AbstractJsqlParser implements ISqlParser {



    /**
     * 解析 SQL 方法
     *
     * @param metaObject 元对象
     * @param sql        SQL 语句
     * @return SQL 信息
     */

    @Override
    public SqlInfo parser(MetaObject metaObject, String sql) {
        if (this.allowProcess(metaObject)) {
            try {
                log.debug("Original SQL: " + sql);
                // fixed github pull/295
                StringBuilder sqlStringBuilder = new StringBuilder();
                Statements statements = CCJSqlParserUtil.parseStatements(sql);
                int i = 0;
                for (Statement statement : statements.getStatements()) {
                    if (null != statement) {
                        if (i++ > 0) {
                            sqlStringBuilder.append(';');
                        }
                        sqlStringBuilder.append(this.processParser(statement).getSql());
                    }
                }
                if (sqlStringBuilder.length() > 0) {
                    return SqlInfo.newInstance().setSql(sqlStringBuilder.toString());
                }
            } catch (JSQLParserException e) {
                String msg = "Failed to process, please exclude the tableName or statementId.\n Error SQL:".concat(sql);
                throw new MybatisTenantException(msg, e);
            }
        }
        return null;
    }

    /**
     * 执行 SQL 解析
     *
     * @param statement JsqlParser Statement
     * @return
     */
    public SqlInfo processParser(Statement statement) {
        if (statement instanceof Insert) {
            this.processInsert((Insert) statement);
        } else if (statement instanceof Select) {
            this.processSelectBody(((Select) statement).getSelectBody());
        } else if (statement instanceof Update) {
            this.processUpdate((Update) statement);
        } else if (statement instanceof Delete) {
            this.processDelete((Delete) statement);
        }
        log.debug("parser sql: " + statement.toString());
        return SqlInfo.newInstance().setSql(statement.toString());
    }

    /**
     * 新增
     */
    public abstract void processInsert(Insert insert);

    /**
     * 删除
     */
    public abstract void processDelete(Delete delete);

    /**
     * 更新
     */
    public abstract void processUpdate(Update update);

    /**
     * 查询
     */
    public abstract void processSelectBody(SelectBody selectBody);

    /**
     * 判断是否允许执行
     * <p>例如：逻辑删除只解析 delete , update 操作</p>
     *
     * @param metaObject 元对象
     * @return true
     */
    public boolean allowProcess(MetaObject metaObject) {
        return true;
    }
}
