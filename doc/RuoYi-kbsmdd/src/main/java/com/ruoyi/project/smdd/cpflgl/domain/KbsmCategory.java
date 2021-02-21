package com.ruoyi.project.smdd.cpflgl.domain;

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
 * 菜品分类管理对象 kbsm_category
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmCategory implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品分类id */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 上级分类id */
    @Excel(name = "上级分类id")
    private Long parentId;

    /** 分类图片id */
    @Excel(name = "分类图片id")
    private Long imageId;

    /** 排序方式(数字越小越靠前) */
    @Excel(name = "排序方式(数字越小越靠前)")
    private Long sort;

    /** 门店id */
    @Excel(name = "门店id")
    private Long shopId;

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
