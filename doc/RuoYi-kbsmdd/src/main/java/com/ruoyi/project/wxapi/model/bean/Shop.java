package com.ruoyi.project.wxapi.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hnkb
 * @since 2020-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 门店logo
     */
    private Integer logoImageId;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 营业时间
     */
    private String shopHours;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所在区/县
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 门店坐标
     */
    private String coordinate;

    /**
     * 门店简介
     */
    private String summary;

    /**
     * 点餐模式 1店内就餐 2打包带走 3外卖直达 4到店自取
     */
    private String foodMode;

    /**
     * 堂食模式(1选桌 2排号)
     */
    private Integer tangMode;

    /**
     * 自提核销(1开启 0关闭)
     */
    private Integer isCheck;

    /**
     * 门店状态(1开启 0关闭)
     */
    private Integer status;

    /**
     * 排序(数字越小越靠前)
     */
    private Integer sort;

    /**
     * 小程序id
     */
    private Integer wxappId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;


}
