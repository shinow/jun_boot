package com.ruoyi.project.wxapi.controller.util;

public class QueryStringUtil {

    public static String[] splitQueryString(String queryString) {
        String[] arr = queryString.split("&");
        return arr;
    }

    public static String removeKeyFromQueryString(String queryString, String key) {
        String retQueryString = "";
        String[] arr = splitQueryString(queryString);
        for (int i = 0; i < arr.length; i++) {
            String[] itemArr = arr[i].split("=");
            if (!itemArr[0].equals(key)) {
                    retQueryString = retQueryString + arr[i] + "&";
            }
        }
        if (retQueryString.length() > 0) {
           retQueryString =  retQueryString.substring(0, retQueryString.length() - 1);
        }
        return retQueryString;
    }

    public static void main(String[] args) {
        String param = "page=2&dsdf=rrrr&ff=222";
        String[] arr = QueryStringUtil.splitQueryString(param);
        System.out.println(QueryStringUtil.removeKeyFromQueryString(param, "dsdf"));
    }
}
