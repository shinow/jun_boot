package com.ruoyi.project.wxapi.model.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 位置坐标
     */
    private String location;

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
     * 街道
     */
    private String street;

    /**
     * 人性化地址
     */
    private String recommend;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 小程序id
     */
    private Integer wxappId;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;


}
