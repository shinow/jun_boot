package com.pearframe.framework.security.handler;


import com.alibaba.fastjson.JSON;
import com.pearframe.framework.web.domain.ResuBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 注销成功处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * Describe: 自定义 Security 注销成功返回结果
     * Author: 就 眠 仪 式
     * CreateTime: 2019/10/23
     * */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        ResuBean resuBean = new ResuBean();

        resuBean.setCode(200);

        resuBean.setMsg("注销成功");

        resuBean.setSuccess(true);

        SecurityContextHolder.clearContext();
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(resuBean));
    }
}
