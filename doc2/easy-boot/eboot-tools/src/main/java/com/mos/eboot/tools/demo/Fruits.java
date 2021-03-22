package com.mos.eboot.tools.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;


@ExcelTarget("fruits")
public class Fruits {

    @Excel(name = "名称")
    private String name;

    @Excel(name = "价格")
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
