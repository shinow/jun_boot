package com.ruoyi.project.wxapi.model.qo;

import lombok.Data;

@Data
public class RegQO {
    private String code;
    // 推荐人
    private String recommender;
    // 场景值
    private String scene;

    private Integer wxapp_id;
}
