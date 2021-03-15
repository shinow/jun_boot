package com.mos.eboot.tools.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import java.util.List;

public class category {

    @Excel(name = "编号")
    private String id;

    @Excel(name = "分类")
    private String categoryName;

    @ExcelCollection(name = "fruits")
    private List<Fruits> fruits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Fruits> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruits> fruits) {
        this.fruits = fruits;
    }
}
