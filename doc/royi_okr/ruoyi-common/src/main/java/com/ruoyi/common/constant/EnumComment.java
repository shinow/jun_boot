package com.ruoyi.common.constant;


/**
 * 定义评论常量
 *
 * @author xiaoshuai2233@sina.com
 */
public enum EnumComment
{
    COMMENT_TYPE_FOR_O(0,1,"o"),

    COMMENT_TYPE_FOR_KR(1,1,"kr"),

    COMMENT_TYPE_FOR_COMMENT(2,1,"comment");

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

    /**
     * 判断某个元素是否包含在本枚举类中
     * @param key
     * @return
     */
    public static Boolean isHave(Integer key) {
        boolean include = false;
        for (EnumComment e: EnumComment.values()){
            if(e.getValue()== key){
                include = true;
                break;
            }
        }
        return include;
    }

    EnumComment(Integer value, Integer code, String desc){
        this.value = value;
        this.type  = code;
        this.desc  = desc;
    }
}