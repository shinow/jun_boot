package com.jeasyuicn.rbac.interceptor;

import com.jeasyuicn.rbac.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : URL权限拦截器</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年12月21日</li>
 * <li>@author     : ____′↘夏悸</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Component
public class RightsInterceptor implements HandlerInterceptor {

    @Value("${system.super.user.id}")
    private Long superId;

    private PathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (!user.getId().equals(superId)) {
            Set<String> urls = (Set<String>) session.getAttribute("urls");
            String path = request.getServletPath();
            for (String url : urls) {
                if (matcher.match(url, path)) {
                    //能匹配到当前的url表示已授权
                    return true;
                }
            }

            if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
                //ajax请求
                response.sendError(403);
            } else {
                response.sendRedirect(request.getContextPath() + "/reject");
            }
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
