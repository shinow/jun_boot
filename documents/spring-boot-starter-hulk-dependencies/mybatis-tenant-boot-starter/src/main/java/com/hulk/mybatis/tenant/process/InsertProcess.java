package com.hulk.mybatis.tenant.process;


import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;

@Deprecated
@UtilityClass
public class InsertProcess {


    public String addWhere(Insert insert, String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        insert.getColumns().add(new Column(tenantIdColumn));
        if (insert.getItemsList() instanceof MultiExpressionList) {
            // 处理同时插入多条数据的情况
            for (ExpressionList expressionList : ((MultiExpressionList) insert.getItemsList()).getExprList()) {
                SqlProcess.addTenantValue(expressionList, tenantIdColumn,tenantContextHolder);
            }
        } else {
            // 处理仅插入一条数据的情况
            SqlProcess.addTenantValue(((ExpressionList) insert.getItemsList()),tenantIdColumn,tenantContextHolder);
        }

        return insert.toString();
    }


}
