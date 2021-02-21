package com.ruoyi.project.wxapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserAddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @JsonProperty("address_id")
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

}
