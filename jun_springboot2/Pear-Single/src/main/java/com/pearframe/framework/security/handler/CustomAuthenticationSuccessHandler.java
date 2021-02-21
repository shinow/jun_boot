package com.pearframe.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.pearframe.common.constant.TokenConstant;
import com.pearframe.common.utils.auth.TokenUtil;
import com.pearframe.framework.security.subject.CustomUserDetails;
import com.pearframe.framework.web.domain.ResuBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 登陆成功处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Describe: 自定义 Security 登陆成功返回结果
     * Author: 就 眠 仪 式
     * CreateTime: 2019/10/23
     * */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 创建 Json Web Token
        CustomUserDetails customUserDetails =(CustomUserDetails) authentication.getPrincipal();
        String token = TokenUtil.createAccessToken(customUserDetails);
        String accessToken = TokenConstant.TOKEN_PREFIX + token;
        // 封装返回数据
        ResuBean resuBean = new ResuBean();
        resuBean.setSuccess(true);
        resuBean.setMsg("登陆成功");
        resuBean.setToken(accessToken);
        resuBean.setCode(200);
        // 返回封装数据
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(resuBean));

    }
}
