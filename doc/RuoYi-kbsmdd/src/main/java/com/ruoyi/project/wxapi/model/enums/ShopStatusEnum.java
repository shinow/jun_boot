package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShopStatusEnum {
    CLOSE(0, "关闭"),
    OPEN(1, "开启");

    private Integer value;
    private String text;
}
