package com.ruoyi.common.constant;

/**
 * 定义点赞常量
 * 
 * @author xiaoshuai2233@sina.com
 */
public enum EnumThumbes
{
    THUMBES_TYPE_FOR_O(0,1,"o"),

    THUMBES_TYPE_FOR_KR(1,1,"kr"),

    THUMBES_TYPE_FOR_COMMENT(2,1,"comment");

    private final Integer value;

    private final Integer type;

    private final String desc;

    @Override
    public String toString() {
        return this.value+"_"+this.type;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    EnumThumbes(Integer value, Integer code, String desc){
        this.value = value;
        this.type  = code;
        this.desc  = desc;
    }
}
