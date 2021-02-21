package com.ruoyi.project.wxapi.model.qo;

import lombok.Data;

@Data
public class SearchQO {
    private String search_field;
    private String keyword;
    // 第几页
    private Integer page;
    // 每页的数据量
    private Integer limit;


}
