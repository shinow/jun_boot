package com.ruoyi.common.constant;

/**
 * 通用OKR常量
 * 定于 OKR 涉及到的常量
 * @author ruoyi
 */
public class ConstantsOkr
{
    /**
     * 默认点赞状态
     */
    public static final Integer THUMBER_DEFAULT_STATES = 0;

    /**
     * 默认评价状态
     */
    public static final Integer COMMENT_DEFAULT_STATES = 0;

    /**
     * 默认的一个评论对应一次输出几条评论
     */
    public static final Integer COMMENT_DEFAULT_OUTPUT = 10;

    /**
     * 评论的额外字段：文件附件
     */
    public static final String COMMENT_META_FILE = "file";

    /**
     * 评论的额外字段：图片附件
     */
    public static final String COMMENT_META_IMAGE = "image";

    /**
     * 评论的额外字段：at人
     */
    public static final String COMMENT_META_AT = "at";

    /**
     * 评论的额外字段：关联okr
     */
    public static final String COMMENT_META_OKR = "okr";

    /**
     * 评论的额外字段：点赞数量
     */
    public static final String COMMENT_META_THUMB = "thumb";


    /**
     * 评论的额外字段：重复点赞间隔时间
     * 一天的时间戳
     */
    public static final Integer COMMENT_REPEAT_THUMB_TIME = 60*60*24;
}
