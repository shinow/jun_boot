package com.hulk.mybatis.tenant.process;


import com.hulk.mybatis.tenant.holder.TenantContextHolder;
import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@Deprecated
@UtilityClass
public class SubSelectProcess {




    /**
     * 递归查询sql中所有的子查询对象
     *
     * @param where 当前sql的where条件 where为AndExpression或OrExpression的实例，解析其中的rightExpression，然后检查leftExpression是否为空，
     *              不为空则是AndExpression或OrExpression，再次解析其中的rightExpression
     *              注意tenantid-where是加在子查询上的
     */
    void findSubSelect(Expression where, String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {

        if (where != null) {
            // and 表达式
            if (where instanceof AndExpression) {
                AndExpression andExpression = (AndExpression) where;
                if (andExpression.getRightExpression() instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) andExpression.getRightExpression();
                    doSelect(subSelect,tenantIdColumn,tenantContextHolder);
                }
                if (andExpression.getLeftExpression() != null) {
                    findSubSelect(andExpression.getLeftExpression(),tenantIdColumn,tenantContextHolder);
                }
            } else if (where instanceof OrExpression) {
                //  or表达式
                OrExpression orExpression = (OrExpression) where;
                if (orExpression.getRightExpression() instanceof SubSelect) {
                    SubSelect subSelect = (SubSelect) orExpression.getRightExpression();
                    doSelect(subSelect,tenantIdColumn,tenantContextHolder);
                }
                if (orExpression.getLeftExpression() != null) {
                    findSubSelect(orExpression.getLeftExpression(),tenantIdColumn,tenantContextHolder);
                }
            } else if (where instanceof EqualsTo) {
                // 不需要处理

            } else {
                throw new OperationNotSupportedException("未解析的SQL，未知的对象类型 " + where.getClass().getTypeName());
            }
        }
    }

    /**
     * 处理表达式对象中的where
     *
     * @param expression 查询语句
     * @throws Exception 租户id异常
     */
    public void doSelect(Expression expression,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {
        PlainSelect ps;

        if (expression instanceof SubSelect) {
            SelectBody selectBody = ((SubSelect) expression).getSelectBody();
            if (selectBody instanceof PlainSelect) {
                addCondition(selectBody,tenantIdColumn,tenantContextHolder);
            } else if (selectBody instanceof SetOperationList) {
                List<SelectBody> selectBodys = ((SetOperationList) selectBody).getSelects();
                for (SelectBody body : selectBodys) {
                    addCondition(body,tenantIdColumn,tenantContextHolder);
                }
            } else {
                throw new OperationNotSupportedException("未解析的SQL，未知的对象类型 " + selectBody.getClass().getTypeName());
            }

        } else if (expression instanceof Select) {
            ps = (PlainSelect) ((Select) expression).getSelectBody();
            addCondition(ps,tenantIdColumn,tenantContextHolder);
        } /*else if (expression instanceof JdbcParameter || expression instanceof LongValue|| expression instanceof StringValue) {
            // 不做处理
        } else {
            throw new OperationNotSupportedException("未解析的SQL，未知的对象类型 " + expression.getClass().getTypeName());
        }*/
    }

    /**
     * 给查询from table 添加条件
     *
     * @param select 查询sql
     * @throws Exception
     */

    void addCondition(SelectBody select,String tenantIdColumn, TenantContextHolder tenantContextHolder) throws Exception {

        PlainSelect ps;

        if (select instanceof Select) {
            ps = (PlainSelect) ((Select) select).getSelectBody();
        } else if (select instanceof SubSelect) {
            ps = (PlainSelect) ((SubSelect) select).getSelectBody();
        } else if (select instanceof PlainSelect) {
            ps = (PlainSelect) select;
        } else {
            throw new Exception("类型转换错误 " + select.toString());
        }

        // 处理主表的where条件
        assert ps != null;
        FromItem fromItem = ps.getFromItem();
        if (fromItem instanceof Table) {
            // from TableName
            Table formTable = (Table) fromItem;
            String tableNameOrAlias = formTable.getAlias() == null ? formTable.getName() : formTable.getAlias().getName();
            if (ps.getWhere() != null) {
                AndExpression where = SqlProcess.addAndExpression(tableNameOrAlias, ps.getWhere(),tenantIdColumn,tenantContextHolder);
                ps.setWhere(where);
            } else {
                ps.setWhere(SqlProcess.addEqualsTo(tableNameOrAlias,tenantIdColumn,tenantContextHolder));
            }

            // 处理join从表的的on条件
            if (ps.getJoins() != null) {
                for (Join join : ps.getJoins()) {
                    Table joinTable = (Table) join.getRightItem();
                    tableNameOrAlias = joinTable.getAlias() == null ? joinTable.getName() : joinTable.getAlias().getName();
                    AndExpression where = SqlProcess.addAndExpression(tableNameOrAlias, join.getOnExpression(),tenantIdColumn,tenantContextHolder);
                    join.setOnExpression(where);
                }
            }

        } else if (fromItem instanceof SubSelect) {
            // from (Select *)
            doSelect((Expression)fromItem,tenantIdColumn,tenantContextHolder);
        } else {
            throw new OperationNotSupportedException("未解析的SQL，未知的对象类型 " + fromItem.getClass().getTypeName());
        }

        // 处理子查询的where
        findSubSelect(ps.getWhere(),tenantIdColumn,tenantContextHolder);
    }


}
