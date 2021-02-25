package com.hulk.mybatis.tenant.util;


import lombok.experimental.UtilityClass;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * jsqlparser解析SQL工具类
 * PlainSelect类不支持union、union all等请使用SetOperationList接口
 *
 */
@UtilityClass
public class SqlParserTool {



    /**
     * 由于jsqlparser没有获取SQL类型的原始工具，并且在下面操作时需要知道SQL类型，所以编写此工具方法
     * @param sql sql语句
     * @return sql类型，
     * @throws JSQLParserException
     */
//    public static SqlType getSqlType(String sql) throws JSQLParserException {
//        Statement sqlStmt = CCJSqlParserUtil.parse(new StringReader(sql));
//        if (sqlStmt instanceof Alter) {
//            return SqlType.ALTER;
//        } else if (sqlStmt instanceof CreateIndex) {
//            return SqlType.CREATEINDEX;
//        } else if (sqlStmt instanceof CreateTable) {
//            return SqlType.CREATETABLE;
//        } else if (sqlStmt instanceof CreateView) {
//            return SqlType.CREATEVIEW;
//        } else if (sqlStmt instanceof Delete) {
//            return SqlType.DELETE;
//        } else if (sqlStmt instanceof Drop) {
//            return SqlType.DROP;
//        } else if (sqlStmt instanceof Execute) {
//            return SqlType.EXECUTE;
//        } else if (sqlStmt instanceof Insert) {
//            return SqlType.INSERT;
//        } else if (sqlStmt instanceof Merge) {
//            return SqlType.MERGE;
//        } else if (sqlStmt instanceof Replace) {
//            return SqlType.REPLACE;
//        } else if (sqlStmt instanceof Select) {
//            return SqlType.SELECT;
//        } else if (sqlStmt instanceof Truncate) {
//            return SqlType.TRUNCATE;
//        } else if (sqlStmt instanceof Update) {
//            return SqlType.UPDATE;
//        } else if (sqlStmt instanceof Upsert) {
//            return SqlType.UPSERT;
//        } else {
//            return SqlType.NONE;
//        }
//    }

    /**
     * 获取sql操作接口,与上面类型判断结合使用
     * example:
     * String sql = "create table a(a string)";
     * SqlType sqlType = SqlParserTool.getSqlType(sql);
     * if(sqlType.equals(SqlType.SELECT)){
     *     Select statement = (Select) SqlParserTool.getStatement(sql);
     *  }
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public  Statement getStatement(String sql) throws JSQLParserException {
        Statement sqlStmt = CCJSqlParserUtil.parse(new StringReader(sql));
        return sqlStmt;
    }

    /**
     * 获取tables的表名
     * @param statement
     * @return
     */
    public  List<String> getTableNameList(Select statement){
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        return tableList;
    }

    public  String convertTable2Name(Table table){

        String tableInfo = table.getName();
        //表存在的索引
        String dbName = null;
        String tableName;
        String[] tableArray = tableInfo.split("\\.");
        if (tableArray.length == 1) {
            tableName = tableArray[0];
        } else {
            dbName = tableArray[0];
            tableName = tableArray[1];
        }
        return tableName;
    }



    /**
     * 获取join层级
     * @param selectBody
     * @return
     */
    public  List<Join> getJoins(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            List<Join> joins =((PlainSelect) selectBody).getJoins();
            return joins;
        }
        return new ArrayList<Join>();
    }

    /**
     *
     * @param selectBody
     * @return
     */
    public  List<Table> getIntoTables(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            List<Table> tables = ((PlainSelect) selectBody).getIntoTables();
            return tables;
        }
        return new ArrayList<Table>();
    }

    /**
     *
     * @param selectBody
     * @return
     */
    public  void setIntoTables(SelectBody selectBody,List<Table> tables){
        if(selectBody instanceof PlainSelect){
            ((PlainSelect) selectBody).setIntoTables(tables);
        }
    }

    /**
     * 获取limit值
     * @param selectBody
     * @return
     */
    public  Limit getLimit(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            Limit limit = ((PlainSelect) selectBody).getLimit();
            return limit;
        }
        return null;
    }

    /**
     * 为SQL增加limit值
     * @param selectBody
     * @param l
     */
    public  void setLimit(SelectBody selectBody,long l){
        if(selectBody instanceof PlainSelect){
            Limit limit = new Limit();
            limit.setRowCount(new StringValue(String.valueOf(l)));
            ((PlainSelect) selectBody).setLimit(limit);
        }
    }

    /**
     * 获取FromItem不支持子查询操作
     * @param selectBody
     * @return
     */
    public  FromItem getFromItem(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
            return fromItem;
        }else if(selectBody instanceof WithItem){
            SqlParserTool.getFromItem(((WithItem) selectBody).getSelectBody());
        }
        return null;
    }

    /**
     * 获取子查询
     * @param selectBody
     * @return
     */
    public  SubSelect getSubSelect(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
            if(fromItem instanceof SubSelect){
                return ((SubSelect) fromItem);
            }
        }else if(selectBody instanceof WithItem){
            SqlParserTool.getSubSelect(((WithItem) selectBody).getSelectBody());
        }
        return null;
    }

    /**
     * 判断是否为多级子查询
     * @param selectBody
     * @return
     */
    public  boolean isMultiSubSelect(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            FromItem fromItem = ((PlainSelect) selectBody).getFromItem();
            if(fromItem instanceof SubSelect){
                SelectBody subBody = ((SubSelect) fromItem).getSelectBody();
                if(subBody instanceof PlainSelect){
                    FromItem subFromItem = ((PlainSelect) subBody).getFromItem();
                    if(subFromItem instanceof SubSelect){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取查询字段
     * @param selectBody
     * @return
     */
    public  List<SelectItem> getSelectItems(SelectBody selectBody){
        if(selectBody instanceof PlainSelect){
            List<SelectItem> selectItems = ((PlainSelect) selectBody).getSelectItems();
            return selectItems;
        }
        return null;
    }

    public static void main(String[] args) throws JSQLParserException {
        Statement sqlStmt = CCJSqlParserUtil.parse(new StringReader("show databases"));

    }

}
