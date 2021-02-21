package com.ruoyi.project.wxapi.model.dto;

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
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderAddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;


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
    private String detail;



}
