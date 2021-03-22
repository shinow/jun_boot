package com.mos.eboot.tools.util;


import java.util.UUID;

/**
 * @author 小尘哥
 * id生成器
 */
public class IDGen {

    public static String genId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
