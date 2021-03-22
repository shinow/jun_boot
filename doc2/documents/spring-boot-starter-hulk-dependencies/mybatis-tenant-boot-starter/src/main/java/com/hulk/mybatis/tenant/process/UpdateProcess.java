package com.hulk.mybatis.tenant.process;

import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.update.Update;


import java.util.Arrays;


@Deprecated
@UtilityClass
public class UpdateProcess {



    public String addWhere(Update update,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        // 针对需要update的表处理增加where条件
        for (Table formTable : update.getTables()) {
            String tableNameOrAlias = formTable.getAlias() == null ? formTable.getName() : formTable.getAlias().getName();
            if (update.getWhere() != null) {
                update.setWhere(SqlProcess.addAndExpression(tableNameOrAlias, update.getWhere(),tenantIdColumn,tenantContextHolder));
            } else {
                throw new Exception("update语句不能没有where条件:" + update.toString() + Arrays.toString(Thread.currentThread().getStackTrace()));
            }
        }

        // 针对where中的子查询  进一步处理
        SubSelectProcess.findSubSelect(update.getWhere(),tenantIdColumn,tenantContextHolder);

        // set 值中的子查询
        for (Expression expression : update.getExpressions()) {
            SubSelectProcess.doSelect(expression,tenantIdColumn,tenantContextHolder);
        }

        return update.toString();
    }


}
