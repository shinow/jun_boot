package com.hulk.mybatis.tenant.Interceptor;


import com.hulk.mybatis.tenant.handler.AbstractSqlParserHandler;
import com.hulk.mybatis.tenant.handler.TenantHandler;
import com.hulk.mybatis.tenant.handler.TenantSqlParser;
import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import com.hulk.mybatis.tenant.parser.SqlParserHelper;
import com.hulk.mybatis.tenant.util.PluginUtils;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * 拦截和处理Mybatis管理的所有SQL请求
 * 用于实现多租户数据层面上的隔离
 * 基于每张表中tenantId列的值识别不同的租户
 */
@Slf4j
@Accessors(chain = true)
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class TenantInterceptor2 extends AbstractSqlParserHandler implements Interceptor {



    public TenantInterceptor2(String tenantIdColumn, TenantContextHolder tenantContextHolder, List<String> ignoreTenantTables,
                              List<String>   mappedStatementIds   ) {
        log.info("plug-in TenantInterceptor2 init success");

        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new StringValue(tenantContextHolder.getTenantId());
            }

            @Override
            public String getTenantIdColumn() {
                return tenantIdColumn;
            }

            @Override
            public boolean doTableFilter(String tableName) {
                 // 这里可以判断是否过滤表
                return ignoreTenantTables.contains(tableName);
            }
        });
        super.sqlParserList.add(tenantSqlParser);
        super.setSqlParserFilter(metaObject -> {
            MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
            // 过滤自定义查询此时无租户信息约束 出现
           if ( mappedStatementIds.contains(ms.getId())){
               return true;
           }
           return false;
        });
     ;
       }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        // SQL 解析
        this.sqlParser(metaObject);
        return invocation.proceed();

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
