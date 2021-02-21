package com.ruoyi.project.wxapi.model.qo;

import lombok.Data;

@Data
public class OrderQO {
    private Integer people;
    private Integer ware_price;
    private String arrive_time;
    private String message;
    private String flavor;
}
