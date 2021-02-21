package com.ruoyi.project.wxapi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.model.bean.WxappPage;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.service.IWxappPageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wxapi/index")
public class WxapiIndexController {
    @Autowired
    IWxappPageService wxappPageServicel;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public Result page(@RequestParam Integer wxapp_id, @RequestParam String shop_id, @RequestParam String token) {
        WxappPage wxappPage = wxappPageServicel.selectByWxAppId(wxapp_id);
        String pageData = wxappPage.getPageData();
        JSONObject pageDataObj = JSONObject.parseObject(pageData);
        JSONArray jsonArray = pageDataObj.getJSONArray("items");
        return Result.success(jsonArray);
    }
}
