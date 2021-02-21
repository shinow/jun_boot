package com.ruoyi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.service.IOkrInfoService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WssController {

    @Autowired
    private IOkrInfoService okrInfoService;

    @RequiresGuest
    @GetMapping("/wss")
    public Map<String,String> test(){
        Map<String,String> rest = new HashMap<>();
        rest.put("name", "xiaming");
        rest.put("uid", "12455442");
        return rest;
    }

    @GetMapping("/login")
    public JSONArray login(){
        OkrInfo okrInfo = new OkrInfo();
        okrInfo.setUserId(114L);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(okrInfoService.selectOkrInfoList(okrInfo)));
        return  array;
    }

}


