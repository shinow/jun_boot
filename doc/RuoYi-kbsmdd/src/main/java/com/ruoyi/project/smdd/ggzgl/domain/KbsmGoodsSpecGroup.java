package com.ruoyi.project.smdd.ggzgl.domain;

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
 * 规格组管理对象 kbsm_goods_spec_group
 * 
 * @author ruoyi
 * @date 2020-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmGoodsSpecGroup implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键(group_id) */
    @Excel(name = "主键(group_id)")
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String groupName;

    /** 商品id */
    @Excel(name = "商品id")
    private Long goodsId;

    /** 小程序id */
    @Excel(name = "小程序id")
    private Long wxappId;

    /** 1无影响库存规格  2影响库存规格 */
    @Excel(name = "1无影响库存规格  2影响库存规格")
    private Integer type;

    /** 创建时间 */
    private Date gmtCreate;

    /** 更新时间 */
    private Date gmtModified;



}
