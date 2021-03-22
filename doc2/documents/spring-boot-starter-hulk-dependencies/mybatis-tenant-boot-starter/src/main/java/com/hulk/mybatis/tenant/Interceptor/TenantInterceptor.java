package com.hulk.mybatis.tenant.Interceptor;



import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import com.hulk.mybatis.tenant.process.*;

import com.hulk.mybatis.tenant.util.SqlFilterUtil;
import com.hulk.mybatis.tenant.util.SqlParserTool;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * 拦截和处理Mybatis管理的所有SQL请求
 * 用于实现多租户数据层面上的隔离
 * 基于每张表中tenantId列的值识别不同的租户
 */

@Deprecated
@Slf4j
@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class}),
        @Signature(method = "query", type = StatementHandler.class, args = {java.sql.Statement.class, ResultHandler.class})})
public class TenantInterceptor implements Interceptor {


    private String tenantIdColumn ;

    private TenantContextHolder tenantContextHolder;
    // 属性 数据库方言
    private final static String dialect = "mysql";

    public TenantInterceptor(String tenantIdColumn,TenantContextHolder tenantContextHolder) {
        log.info("plug-in TenantInterceptor init success");
        this.tenantIdColumn = tenantIdColumn;
        this.tenantContextHolder = tenantContextHolder;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler handler = (StatementHandler) invocation.getTarget();
        // 由于mappedStatement为protected的，所以要通过反射获取
        MetaObject statementHandler = SystemMetaObject.forObject(handler);
        // mappedStatement中有我们需要的方法id
        MappedStatement mappedStatement = (MappedStatement) statementHandler.getValue("delegate.mappedStatement");
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql();
        String id = mappedStatement.getId();


        if (tenantContextHolder.getTenantId() != null) {
            // 获得方法类型
            String newSql = addWhere(sql);
            if (newSql != null) {
                statementHandler.setValue("delegate.boundSql.sql", newSql);
            }
        }


        return invocation.proceed();
    }

    /**
     * 处理具体的SQL，在合适的地方添加租户id的限定条件
     *
     * @param sql 原始SQL
     * @return 处理后的SQL
     * @throws JSQLParserException SQL转换异常
     */
    private String addWhere(String sql) throws Exception {
        String newSql = null;
        // sql字符串转换成对象形式
        Statement stmt;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (Exception e) {
            throw new Exception("SQL转换失败:" + sql + "\r\n" + Arrays.toString(e.getStackTrace()));
        }


        // 针对不同类型的SQL 做出不同处理
        if (stmt instanceof Insert) {

            Insert insert =   (Insert) stmt;
            Table table =  insert.getTable();
            String name = SqlParserTool.convertTable2Name(table);
            if(SqlFilterUtil.blackFilter(name)){
               return sql;
            }
            newSql = InsertProcess.addWhere(insert,tenantIdColumn,tenantContextHolder);
        } else if (stmt instanceof Delete) {

            Delete delete =   (Delete) stmt;
            Table table =  delete.getTable();
            String name = SqlParserTool.convertTable2Name(table);
            if(SqlFilterUtil.blackFilter(name)){
                return sql;
            }
            newSql = DeleteProcess.addWhere(delete,tenantIdColumn,tenantContextHolder);
        } else if (stmt instanceof Update) {

            Update update =   (Update) stmt;
            List<String> names =  update.getTables().stream().map(v->SqlParserTool.convertTable2Name(v)).collect(Collectors.toList());
            if(SqlFilterUtil.blackOrFilter(names)){
                return sql;
            }
            newSql = UpdateProcess.addWhere(update,tenantIdColumn,tenantContextHolder);
        } else if (stmt instanceof Select) {

            Select select =   (Select) stmt;
            List<String> names = SqlParserTool.getTableNameList(select);
            if(SqlFilterUtil.blackOrFilter(names)){
                return sql;
            }
            newSql = SelectProcess.addWhere(select,tenantIdColumn,tenantContextHolder);
        } else if (stmt instanceof CreateTable) {

            CreateTable createTable =   (CreateTable) stmt;
            Table table =  createTable.getTable();
            String name = SqlParserTool.convertTable2Name(table);
            if(SqlFilterUtil.blackFilter(name)){
                return sql;
            }
            newSql = CreateProcess.addWhere(stmt);
        }

        return newSql;
    }

    /**
     * 生成代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }



}