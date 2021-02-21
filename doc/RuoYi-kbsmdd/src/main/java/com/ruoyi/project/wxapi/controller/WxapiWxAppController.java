package com.ruoyi.project.wxapi.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.model.bean.Wxapp;
import com.ruoyi.project.wxapi.model.bean.WxappHelp;
import com.ruoyi.project.wxapi.model.bean.WxappPage;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.dto.WxappDTO;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.service.IWxappHelpService;
import com.ruoyi.project.wxapi.model.service.IWxappPageService;
import com.ruoyi.project.wxapi.model.service.IWxappService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("wxapp")
@RestController
@RequestMapping("/wxapi/wxapp")
public class WxapiWxAppController {
    @Autowired
    IWxappService wxappService;
    @Autowired
    IWxappPageService wxappPageServicel;
    @Autowired
    IWxappHelpService wxappHelpService;

    @RequestMapping(value = "/base", method = RequestMethod.GET)
    public Result base(BaseParamQO baseParamQO) {
        Wxapp wxapp = wxappService.selectByWxAppId(baseParamQO.getWxapp_id());
        WxappPage wxappPage = wxappPageServicel.selectByWxAppId(baseParamQO.getWxapp_id());
       String pageData = wxappPage.getPageData();
        JSONObject pageDataObj = JSONObject.parseObject(pageData);
        JSONObject pageObj = pageDataObj.getJSONObject("page");

        WxappDTO wxappDTO = new WxappDTO();
        BeanUtil.copyProperties(wxapp, wxappDTO);

        JSONObject data = (JSONObject) JSONObject.toJSON(wxappDTO);
        data.put("navbar", pageObj);
        return Result.success(data);
    }

    @RequestMapping("/help")
    public Result help(BaseParamQO baseParamQO) {
        List<WxappHelp> wxappHelps = wxappHelpService.selectByWxappId(baseParamQO.getWxapp_id());
        JSONObject data = new JSONObject();
        data.put("list", wxappHelps);
        return Result.success(data);
    }


}
