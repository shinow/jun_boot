package com.hulk.mybatis.tenant.process;


import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.delete.Delete;



/**
 * 针对 delete语句的处理
 */
@Deprecated
@UtilityClass
public class DeleteProcess {





    public String addWhere(Delete delete, String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        Expression where = delete.getWhere();
        if (where instanceof BinaryExpression) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(tenantIdColumn));
            equalsTo.setRightExpression(new StringValue(tenantContextHolder.getTenantId()));
            AndExpression andExpression = new AndExpression(equalsTo, where);
            delete.setWhere(andExpression);
        }

        // 针对where中的子查询  进一步处理
        SubSelectProcess.findSubSelect(delete.getWhere(),tenantIdColumn,tenantContextHolder);
        return delete.toString();
    }




}

