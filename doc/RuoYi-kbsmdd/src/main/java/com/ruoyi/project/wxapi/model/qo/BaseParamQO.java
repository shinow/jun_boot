package com.ruoyi.project.wxapi.model.qo;

import lombok.Data;

@Data
public class BaseParamQO {
    private Integer wxapp_id;
    private Integer shop_id;
    private String token;
    private String location;
    private String code;
    private Integer food_mode;
    private Integer tableId;
    private Integer order_id;

}
