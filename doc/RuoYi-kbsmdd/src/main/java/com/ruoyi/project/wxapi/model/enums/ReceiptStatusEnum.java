package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReceiptStatusEnum {
    YCZ(1, "用餐中"),
    YCWB(2, "用餐完毕");

    private Integer value;
    private String text;
}
