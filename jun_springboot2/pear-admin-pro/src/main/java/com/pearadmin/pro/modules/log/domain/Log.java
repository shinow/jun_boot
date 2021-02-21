package com.pearadmin.pro.modules.log.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.pro.common.aop.lang.enums.Business;
import com.pearadmin.pro.common.aop.lang.enums.BusinessKind;
import com.pearadmin.pro.common.aop.lang.enums.BusinessStatus;
import com.pearadmin.pro.common.web.base.BaseDomain;
import lombok.Data;

/**
 * Describe: LOG 实体
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class Log extends BaseDomain {

    /**
     * 编号
     * */
    @TableId
    private String id;

    /**
     * 标题
     * */
    @TableField
    private String title;

    /**
     * 描述
     * */
    @TableField
    private String describe;

    /**
     * 参数
     * */
    @TableField
    private String param;

    /**
     * 方法
     * */
    @TableField
    private String method;

    /**
     * 类型
     * */
    @TableField
    private BusinessKind kind;

    /**
     * 状态
     * */
    @TableField
    private BusinessStatus status;

    /**
     * 业务
     * */
    @TableField
    private Business business;

    /**
     * 路径
     * */
    @TableField
    private String href;

    /**
     * 耗时
     * */
    @TableField
    private String time;

    /**
     * 地址
     * */
    @TableField
    private String address;

    /**
     * IP 地址
     * */
    @TableField
    private String ip;

    /**
     * 浏览器
     * */
    @TableField
    private String browser;

    /**
     * 结果
     * */
    @TableField
    private String result;

    /**
     * 系统
     * */
    @TableField
    private String system;

}
