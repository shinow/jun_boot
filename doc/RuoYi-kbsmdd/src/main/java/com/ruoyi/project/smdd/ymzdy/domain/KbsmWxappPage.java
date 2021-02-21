package com.ruoyi.project.smdd.ymzdy.domain;

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
 * 页面自定义对象 kbsm_wxapp_page
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmWxappPage implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 页面id(主键) */
    private Long id;

    /** 页面类型(1首页 2自定义页) */
    @Excel(name = "页面类型(1首页 2自定义页)")
    private Integer pageType;

    /** 页面数据 */
    @Excel(name = "页面数据")
    private String pageData;

    /** 微信小程序id */
    @Excel(name = "微信小程序id")
    private Long wxappId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtCreate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;



}
