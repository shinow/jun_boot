package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    WAIT(1, "待支付"),
    PAY(2, "已支付");

    private Integer value;
    private String text;
}
