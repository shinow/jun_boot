package com.ruoyi.project.wxapi.model.bean;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Cart {
    private Integer goodsId;
    private Integer goodsNum;
    private Integer goodsSkuId;
    // 无关联库存属性
    private Map<String, Integer> attr;
    private LocalDateTime gmtCreateTime;
}
