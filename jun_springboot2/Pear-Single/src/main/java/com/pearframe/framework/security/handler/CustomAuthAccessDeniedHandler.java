package com.pearframe.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.pearframe.framework.web.domain.ResuBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户暂无权限处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Describe: 自定义 Security 用户暂无权限返回结果
     * Author: 就 眠 仪 式
     * CreateTime: 2019/10/23
     * */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        ResuBean resuBean = new ResuBean();

        resuBean.setCode(403);
        resuBean.setMsg("Not Found Permission");
        resuBean.setSuccess(false);

        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(resuBean));
    }
}
