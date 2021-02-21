/*
 *  Author LiuNan
 */
package com.ruoyi.framework.datasource;

public class DataSourceHolder {
    private static final ThreadLocal<String> source = new ThreadLocal<String>();

    public static void setDataSource(String s) {
        source.set(s);
    }

    public static String getDataSource() {
        return source.get();
    }

    public static void clearDataSource() {
        source.remove();
    }
}
