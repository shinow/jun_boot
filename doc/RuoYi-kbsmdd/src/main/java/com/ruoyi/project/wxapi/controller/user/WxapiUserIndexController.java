package com.ruoyi.project.wxapi.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.wxapi.controller.util.JWTUtil;
import com.ruoyi.project.wxapi.model.bean.User;
import com.ruoyi.project.wxapi.model.bean.UserGrade;
import com.ruoyi.project.wxapi.model.dto.Result;
import com.ruoyi.project.wxapi.model.qo.BaseParamQO;
import com.ruoyi.project.wxapi.model.service.IUserGradeService;
import com.ruoyi.project.wxapi.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/wxapi/user/index")
@RestController
public class WxapiUserIndexController {
    @Autowired
    IUserService userService;
    @Autowired
    IUserGradeService userGradeService;

    @RequestMapping("/detail")
    public Result detail(BaseParamQO baseParamQO) {
        User user = userService.selectByUserId(JWTUtil.parseJWT(baseParamQO.getToken()));
        JSONObject userInfoObj = new JSONObject();
        if (user.getUserGradeId() != 0) {
            userInfoObj.put("mobile", user.getMobile());
            UserGrade userGrade = userGradeService.getByUserGradeId(user.getUserGradeId());
            JSONObject gradeObj = new JSONObject();
            gradeObj.put("name", userGrade.getName());
            userInfoObj.put("grade", gradeObj);
        }

        userInfoObj.put("wallet", user.getWallet());
        userInfoObj.put("commission", user.getCommission());
        userInfoObj.put("score", user.getScore());
        JSONObject data = new JSONObject();
        data.put("userInfo", userInfoObj);

        return Result.success(data);
    }


}
