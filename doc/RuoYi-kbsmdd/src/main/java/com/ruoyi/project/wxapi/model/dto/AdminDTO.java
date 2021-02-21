package com.ruoyi.project.wxapi.model.dto;

import com.ruoyi.project.wxapi.model.bean.Admin;
import com.ruoyi.project.wxapi.model.bean.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class AdminDTO {
    private Admin admin;
    private List<SysRole> roles;
}
