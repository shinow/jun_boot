package com.ifast.common.base;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 业务表基础公共字段
 *
 * deleted 由 mybatis-plus.global-config.sql-injector:com.baomidou.mybatisplus.mapper.LogicSqlInjector 自动维护
 * version 由 MyBatisConfig.optimisticLockerInterceptor 自动维护
 *
 * 以下字段由 meta-object-handler: com.ifast.common.mp.MpMetaObjectHandler 自动维护
 * createAt
 * createBy
 * updateAt
 * updateBy
 * </pre>
 * @author Aron
 */
@Data
@SuppressWarnings("serial")
public class BaseDO implements Serializable {

    @TableLogic
    private Boolean deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private Date createAt;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateAt;
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;
}
