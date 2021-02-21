package com.ruoyi.project.wxapi.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.project.wxapi.config.WxMaConfiguration;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.bean.UserGrade;
import com.ruoyi.project.wxapi.model.bean.Wxapp;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.qo.RegQO;
import com.ruoyi.project.wxapi.model.service.IUserGradeService;
import com.ruoyi.project.wxapi.model.service.IUserService;
import com.ruoyi.project.wxapi.model.service.IWxappService;
import io.swagger.annotations.Api;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/wxapi/user")
public class WxapiUserController {
    private static Logger log = LoggerFactory.getLogger(WxapiUserController.class);
    @Autowired
    IUserService userService;
    @Autowired
    IWxappService wxappService;
    @Autowired
    IUserGradeService userGradeService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(BaseParamQO baseParamQO) {
        Wxapp wxapp = wxappService.selectByWxAppId(baseParamQO.getWxapp_id());

        final WxMaService wxService = WxMaConfiguration.getMaService(wxapp.getAppId());
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(baseParamQO.getCode());
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            User user = userService.selectByOpenId(session.getOpenid());
            if (user == null) {
                return Result.error("未注册");
            } else {
                String jwtToken = JWTUtil.generateJWT(user.getId());
                JSONObject data = new JSONObject();
                data.put("user_id", user.getId());
                data.put("token", jwtToken);
                return Result.success(data);
            }

        } catch (WxErrorException e) {
            e.printStackTrace();;
            return Result.error("");
        }

    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Result reg(RegQO regQO) {
        Wxapp wxapp = wxappService.selectByWxAppId(regQO.getWxapp_id());
        final WxMaService wxService = WxMaConfiguration.getMaService(wxapp.getAppId());
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(regQO.getCode());
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            User user = userService.selectByOpenId(session.getOpenid());
            if (user == null) {
                user = new User();
                user.setOpenId(session.getOpenid());
                user.setRecommender(Integer.parseInt(regQO.getRecommender()));
                user.setScene(Integer.parseInt(regQO.getScene()));
                user.setWxappId(regQO.getWxapp_id());
                userService.insertUser(user);
                user = userService.selectByOpenId(session.getOpenid());
                JSONObject data = new JSONObject();
                data.put("user_id", user.getId());
                return Result.success(data);
            } else {
                return Result.error("已注册");
            }

        } catch (WxErrorException e) {
            e.printStackTrace();;
            return Result.error("获取openid错误");
        }

    }

    @RequestMapping("/getPhoneNumber")
    public Result getPhoneNumber(BaseParamQO baseParamQO) {
        User user = userService.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        List<UserGrade> userGradeList = userGradeService.selectByWxAppId(baseParamQO.getWxapp_id());
        for (int i = 0; i < userGradeList.size(); i++) {
            UserGrade userGrade = userGradeList.get(i);
            if (user.getScore() >= userGrade.getScore()) {
                user.setUserGradeId(userGrade.getId());
                userService.updateById(user);
                return Result.success("开通会员成功");
            }
        }
        return Result.error("积分不足");

    }
}
