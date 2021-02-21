package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliverStatusEnum {
    PZZ(1, "烹制中"),
    SCZ(2, "上餐中");

    private Integer value;
    private String text;
}
