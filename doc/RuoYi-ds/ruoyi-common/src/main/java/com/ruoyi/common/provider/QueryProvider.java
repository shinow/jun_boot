package com.ruoyi.common.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class QueryProvider {

    public String queryByParam(Map<String,String> param) {
        String table = param.get("table");
        String field = param.get("field");
        String condition = param.get("condition");
        String startRow = param.get("startRow");
        String pageSize = param.get("pageSize");
        String orderStr = param.get("orderStr");
        SQL sql = new SQL().SELECT(field).FROM(table);
        sql.WHERE(condition);
        String sqlStr ="";
        if(orderStr != null){
            sqlStr = sql.toString() + orderStr + " limit " + startRow + "," +pageSize;
        }else{
            sqlStr = sql.toString() + " limit " + startRow + "," +pageSize;
        }
//        System.out.println(sqlStr);
        return sqlStr;
    }
}
