package com.hulk.mybatis.tenant.util;




import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@UtilityClass
public class SqlFilterUtil {

    /**
     * 初始化查询白名单   不需要加租户id过滤的
     */

    private  final  List<String> blackList = new ArrayList<String>() ;


    public void addBlackList(String args []) {
        blackList.addAll(Arrays.asList(args));
    }

    /**
     * 判断 是否 在黑名单里面
     *
     * @param tableName
     * @return 是否需要过滤
     */

    public boolean blackFilter(String tableName) {
        return (blackList.contains(tableName) );
    }

    /**
     * 判断 是否 在黑名单里面 如果多个表有一个表非黑名单 返回true
     *
     * @param tableNames
     * @return 是否需要过滤
     */
    public boolean blackOrFilter(List<String> tableNames) {
        return  (tableNames.stream().anyMatch((v)->
                blackList.stream().anyMatch(t-> t.equals(v))));
    }

    /**
     * 判断 是否 在黑名单里面 如果多个表有一个表非黑名单 返回false
     *
     * @param tableNames
     * @return 是否需要过滤
     */
    public boolean blackAndFilter(List<String> tableNames) {
        return  (tableNames.stream().allMatch((v)->
                blackList.stream().anyMatch(t->t.equals(v))));
    }
}
