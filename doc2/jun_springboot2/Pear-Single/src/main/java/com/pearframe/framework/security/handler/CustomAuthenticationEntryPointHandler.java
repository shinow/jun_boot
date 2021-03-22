package com.pearframe.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.pearframe.framework.web.domain.ResuBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户未登陆处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    /**
     * Describe: 自定义 Security 用户未登录返回结果
     * Author: 就 眠 仪 式
     * CreateTime: 2019/10/23
     * */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResuBean resuBean = new ResuBean();

        resuBean.setSuccess(false);
        resuBean.setMsg("Not Login Account");
        resuBean.setCode(401);
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(resuBean));
    }
}
