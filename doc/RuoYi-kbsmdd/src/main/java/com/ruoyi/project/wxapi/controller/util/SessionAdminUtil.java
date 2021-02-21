package com.ruoyi.project.wxapi.controller.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.wxapi.model.bean.Admin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class SessionAdminUtil {

    /**
     * 从subject中获取admin对象
     * @return
     */
    public static Admin getAdminFromSecurityUtils() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Object objAdmin = subject.getPrincipals();
            Admin admin = new Admin();
            JSONObject jsonObject = (JSONObject) JSON.toJSON(objAdmin);
            admin = JSON.parseObject(jsonObject.getJSONObject("primaryPrincipal").toString(), Admin.class);
            return admin;
        } else {
            return null;
        }

    }
}
