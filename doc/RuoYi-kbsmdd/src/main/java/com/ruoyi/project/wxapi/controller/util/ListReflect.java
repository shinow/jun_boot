package com.ruoyi.project.wxapi.controller.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 利用反射机制获取list中的值
 */
public class ListReflect {

    /**
     * 获取list中的某个字段并以set形式返回
     * @param list
     * @param fieldName
     * @param <T>
     * @return
     * @throws Exception
     */
    public static<T> Set<?> listToSet(Collection<T> list, String fieldName) {
        Set<Object> ret = new HashSet<>();
        List<String> getStrs = null;
        List<Method> getMethods = new ArrayList<Method>();
        for(T t : list){
            if(getStrs == null){
                getStrs = new ArrayList<String>();
                for(String s : fieldName.split("\\.")){
                    getStrs.add("get" + s.substring(0,1).toUpperCase() + s.substring(1));
                }
            }
            Object value = t;
            for(int i = 0; i < getStrs.size(); i++){

                if(getMethods == null || getMethods.size() <= i ||getMethods.get(i) == null){
                    try {
                        getMethods.add(value.getClass().getDeclaredMethod(getStrs.get(i)));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    value = getMethods.get(i).invoke(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            ret.add(value);
        }
        return ret;
    }

    public static<T> List<?> listToEqualValueList(Collection<T> list, String fieldName, Object eqValue) {
        List<T> ret = new ArrayList<>();
        List<String> getStrs = null;
        List<Method> getMethods = new ArrayList<Method>();
        for(T t : list){
            if(getStrs == null){
                getStrs = new ArrayList<String>();
                for(String s : fieldName.split("\\.")){
                    getStrs.add("get" + s.substring(0,1).toUpperCase() + s.substring(1));
                }
            }
            Object value = t;
            for(int i = 0; i < getStrs.size(); i++){

                if(getMethods == null || getMethods.size() <= i ||getMethods.get(i) == null){
                    try {
                        getMethods.add(value.getClass().getDeclaredMethod(getStrs.get(i)));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    value = getMethods.get(i).invoke(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (eqValue.equals(value)){
                ret.add(t);
            }

        }
        return ret;
    }

    public static void main(String[] args) throws Exception {

    }
}
