package com.lin.config.mybatis;

import com.lin.config.datasource.DynamicDataSourceContextHolder;
import com.lin.config.datasource.DynamicDataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: LinZhaoguan
 * Date: 2018-03-17
 * Time: 17:06
 */
@Slf4j
//@Component
@Order(1)
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}) })
public class RwInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Connection conn = (Connection) invocation.getArgs()[0];
        conn = this.unwrapConnection(conn);
//        if (conn instanceof ConnectionProxy) {
            //强制走写库
            /*if(ConnectionHold.FORCE_WRITE.get() != null && ConnectionHold.FORCE_WRITE.get()){
                routeConnection(ConnectionHold.WRITE, conn);
                return invocation.proceed();
            }*/
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaObject = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
            MappedStatement mappedStatement = null;
            if (statementHandler instanceof RoutingStatementHandler) {
                mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            } else {
                mappedStatement = (MappedStatement) metaObject.getValue("mappedStatement");
            }
            String key = DynamicDataSourceType.MASTER.name();

            if (mappedStatement.getSqlCommandType() == SqlCommandType.SELECT && !mappedStatement.getId().endsWith("!selectKey")) {
                key = DynamicDataSourceType.SLAVEALPHA.name();
            }
            this.routeConnection(key, conn);
//        }

        return invocation.proceed();
    }

    private void routeConnection(String key, Connection conn) {
        DynamicDataSourceContextHolder.setDataSourceKey(key);
        // 同一个线程下保证最多只有一个写数据链接和读数据链接
        if (!DynamicDataSourceContextHolder.containDataSourceKey(key)) {
            ConnectionProxy conToUse = (ConnectionProxy) conn;
            conn = conToUse.getTargetConnection();
            if (DynamicDataSourceType.SLAVEALPHA.name().equals(key)) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DynamicDataSourceContextHolder.setDataSourceKey(key);
        }
//        log.debug("Change DataSource to [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
    }

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

    /**
     * MyBatis wraps the JDBC Connection with a logging proxy but Spring registers the original connection so it should
     * be unwrapped before calling {@code DataSourceUtils.isConnectionTransactional(Connection, DataSource)}
     *
     * @param connection May be a {@code ConnectionLogger} proxy
     * @return the original JDBC {@code Connection}
     */
    private Connection unwrapConnection(Connection connection) {
        if (Proxy.isProxyClass(connection.getClass())) {
            InvocationHandler handler = Proxy.getInvocationHandler(connection);
            if (handler instanceof ConnectionLogger) {
                return ((ConnectionLogger) handler).getConnection();
            }
        }
        return connection;
    }
}
