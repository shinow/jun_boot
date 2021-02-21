package com.ruoyi.project.wxapi.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    JXZ(1, "进行中"),
    QX(2, "取消"),
    DQX(3, "待取消"),
    YWC(4, "已完成");

    private Integer value;
    private String text;
}
