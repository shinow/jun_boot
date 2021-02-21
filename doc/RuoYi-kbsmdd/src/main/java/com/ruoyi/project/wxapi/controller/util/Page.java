package com.ruoyi.project.wxapi.controller.util;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    // 当前页数
    private Integer page;
    //每页的数量
    private Integer limit;
    // 为数据库的起始下标
    private Integer start;
    //数据总数
    private Integer total;
    // 总页数
    private Integer totalPage;
    // 彩虹分页参数
    private int[] rainbow;
    private List<T> data;

    private String href;

    public Page() {

    }

    public Page(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
        this.start = (page - 1) * limit;
    }
    public Page(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public Page(Integer page, Integer limit, Integer total, List<T> data) {
        this.page = page;
        this.limit = limit;
        this.total = total;
        this.data = data;
        this.totalPage = total % limit == 0 ? total / limit : total / limit + 1;
    }

}
