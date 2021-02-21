package com.ruoyi.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ruoyi.controller.AesToken;
import com.ruoyi.domain.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;

/**
 * 通过改过滤器跳到Realm中认证用户信息和权限信息
 * @author gyc
 * @date 2018-10-18
 */
@Configuration
public class AesFilter extends BasicHttpAuthenticationFilter  {
    /**
     * 过滤方法
     */
    protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object object){
        String token=((HttpServletRequest) request).getHeader("token");
        Response jsonResult = new Response();
        //判断请求的请求头是否带上 "Token"
        if (token!=null) {
            try {
                AesToken m = new AesToken(token);
                getSubject(request, response).login(m);
                return true;
            } catch (AuthenticationException e) {
                //token 错误
                e.printStackTrace();
                jsonResult.setCode(500);//返回失败的code
                jsonResult.setState(500);//返回失败的state
                jsonResult.setDescription("token错误,或token已过期");
                jsonResult.setResults(null);
                JSONObject jsonObject=new JSONObject(jsonResult);
                responseOutWithJson(response,jsonObject.toString());
                return false;
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }
    /**
     * 以JSON格式输出
     * @param response
     */
    protected void responseOutWithJson(ServletResponse response,Object responseObject) {
        //将实体对象转换为JSON Object转换
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();
            out.append((String)responseObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
