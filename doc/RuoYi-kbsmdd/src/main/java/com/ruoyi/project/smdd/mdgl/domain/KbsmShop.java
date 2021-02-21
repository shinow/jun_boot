package com.ruoyi.project.smdd.mdgl.domain;

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
 * 门店管理对象 kbsm_shop
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KbsmShop implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String shopName;

    /** 门店logo */
    @Excel(name = "门店logo")
    private Long logoImageId;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 营业时间 */
    @Excel(name = "营业时间")
    private String shopHours;

    /** 所在省份 */
    @Excel(name = "所在省份")
    private String province;

    /** 所在城市 */
    @Excel(name = "所在城市")
    private String city;

    /** 所在区/县 */
    @Excel(name = "所在区/县")
    private String district;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 门店坐标 */
    @Excel(name = "门店坐标")
    private String coordinate;

    /** 门店简介 */
    @Excel(name = "门店简介")
    private String summary;

    /** 点餐模式 1店内就餐 2打包带走 3外卖直达 4到店自取 */
    @Excel(name = "点餐模式 1店内就餐 2打包带走 3外卖直达 4到店自取")
    private String foodMode;

    /** 堂食模式(1选桌 2排号) */
    @Excel(name = "堂食模式(1选桌 2排号)")
    private Integer tangMode;

    /** 自提核销(1开启 0关闭) */
    @Excel(name = "自提核销(1开启 0关闭)")
    private Integer isCheck;

    /** 门店状态(1开启 0关闭) */
    @Excel(name = "门店状态(1开启 0关闭)")
    private Integer status;

    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

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
