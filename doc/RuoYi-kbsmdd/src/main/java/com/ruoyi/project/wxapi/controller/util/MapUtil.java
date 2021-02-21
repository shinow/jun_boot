package com.ruoyi.project.wxapi.controller.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapUtil {

    public static Object convert2Object(Class clazz, Map<String,Object[]> map) throws
            IntrospectionException, InstantiationException, IllegalAccessException{
        BeanInfo bi = Introspector.getBeanInfo(clazz);
        
        Object obj = clazz.newInstance();
        
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        
        String pName;
        for(PropertyDescriptor pd:pds){
            pName = pd.getName();
            if(map.containsKey(pName)){
                try {
                    pd.getWriteMethod().invoke(obj, map.get(pName)[0]);
                } catch (Exception ex) {
                    Logger.getLogger(MapUtil.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
        return obj;
    }
}