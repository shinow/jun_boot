package com.pearframe.framework.security.authent;

import com.alibaba.fastjson.JSONObject;
import com.pearframe.common.constant.TokenConstant;
import com.pearframe.framework.security.subject.CustomUserDetails;
import com.pearframe.framework.web.domain.ResuBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Describe: 自定义 Security Basic Filter 主要增加 Token的验证
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public class CustomTokenAuthenticationFilter extends BasicAuthenticationFilter {

    public static final Logger log = LoggerFactory.getLogger(CustomTokenAuthenticationFilter.class);

    public CustomTokenAuthenticationFilter(AuthenticationManager authenticationManager){

        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获得TokenHeader
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        if (null!=tokenHeader && tokenHeader.startsWith(TokenConstant.TOKEN_PREFIX)) {

            ResuBean resuBean = new ResuBean();

            resuBean.setSuccess(false);

            resuBean.setCode(500);


            try {
                // 获取请求头中JWT的Token
                if (!StringUtils.isEmpty(request.getHeader(TokenConstant.TOKEN_HEADER))) {
                    // 截取JWT前缀
                    String token = request.getHeader(TokenConstant.TOKEN_HEADER).replace(TokenConstant.TOKEN_PREFIX, "");
                    // 解析JWT
                    Claims claims = Jwts.parser()
                            .setSigningKey(TokenConstant.SECRET)
                            .parseClaimsJws(token)
                            .getBody();
                    // 获取用户名
                    String username = claims.getSubject();
                    String userId=claims.getId();
                    if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(userId)) {
                        // 获取角色
                        Set<GrantedAuthority> authorities = new HashSet<>();
                        String authority = claims.get("authorities").toString();
                        if(!StringUtils.isEmpty(authority)){
                            List<Map<String,String>> authorityMap = JSONObject.parseObject(authority, List.class);
                            for(Map<String,String> role : authorityMap){
                                if(!StringUtils.isEmpty(role)) {
                                    authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                                }
                            }
                        }
                        //组装参数
                        CustomUserDetails selfUserEntity = new CustomUserDetails();
                        selfUserEntity.setUserName(claims.getSubject());
                        selfUserEntity.setUserId(claims.getId());
                        selfUserEntity.setAuthorities(authorities);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (ExpiredJwtException e){

                log.error("Json Web Token 超 时 提 醒");
                e.printStackTrace();


            } catch (Exception e) {

                log.error("无 效 的 Json Web Token");
                e.printStackTrace();
            }
        }
        chain.doFilter(request, response);
        return;
    }
}
