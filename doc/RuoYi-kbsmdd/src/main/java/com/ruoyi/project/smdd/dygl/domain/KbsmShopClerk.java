package com.ruoyi.project.smdd.dygl.domain;

import java.util.Date;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 店员管理对象 kbsm_shop_clerk
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmShopClerk implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 开放平台识别号 */
    @Excel(name = "开放平台识别号")
    private String unionId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 门店ID */
    @Excel(name = "门店ID")
    private Long shopId;

    /** 店员姓名 */
    @Excel(name = "店员姓名")
    private String realName;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 密码 */
    @Excel(name = "密码")
    private String pwd;

    /** 身份(1店员 2店长 3 配送) */
    @Excel(name = "身份(1店员 2店长 3 配送)")
    private Integer status;

    /** 小程序id */
    @Excel(name = "小程序id")
    private Long wxappId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;



}
