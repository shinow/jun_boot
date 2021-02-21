package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliverModeEnum {
    ZP(0, "自配"),
    SF(1, "顺丰"),
    DD(2, "达达"),
    UU(3, "UU");

    private Integer value;
    private String text;
}
