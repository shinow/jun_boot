package com.hulk.mybatis.tenant.process;

import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

@Deprecated
@UtilityClass
public class SqlProcess {



    /**
     * insert语句 添加插入租户id列
     *
     * @param expressionList 插入列的list
     * @throws Exception
     */
    public void addTenantValue(ExpressionList expressionList,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        for (Expression expression : expressionList.getExpressions()) {
            if (expression instanceof SubSelect) {
                SubSelectProcess.doSelect(expression,tenantIdColumn,tenantContextHolder);
            }
        }
        expressionList.getExpressions().add(new StringValue(tenantContextHolder.getTenantId()));
    }

    /**
     * 多条件情况下，使用AndExpression给where条件加上tenantid条件
     *
     * @param table 添加条件的表的名字
     * @param where 需要追加条件的where语句
     * @return
     * @throws Exception
     */
    public AndExpression addAndExpression(String table, Expression where,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        EqualsTo equalsTo = addEqualsTo(table,tenantIdColumn,tenantContextHolder);

        return new AndExpression(equalsTo, where);
    }

    /**
     * 创建一个EqualsTo相同判断
     *
     * @param table 表名
     * @return “A=B” 单个where条件表达式
     * @throws Exception
     */
    public EqualsTo addEqualsTo(String table, String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        EqualsTo equalsTo = new EqualsTo();

        equalsTo.setLeftExpression(new Column(table + '.' + tenantIdColumn));
        equalsTo.setRightExpression(new StringValue(tenantContextHolder.getTenantId()));

        return equalsTo;
    }




}
