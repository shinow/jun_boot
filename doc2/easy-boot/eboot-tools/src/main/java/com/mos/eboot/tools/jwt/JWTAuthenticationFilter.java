package com.mos.eboot.tools.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {
	
	static final String HEADER_STRING = "Authorization";
	static final String TOKEN_PREFIX = "Bearer";
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	
    	HttpServletRequest req = (HttpServletRequest)request;
    	
    	String token = req.getHeader(HEADER_STRING);
    	if(StringUtils.isNotBlank(token) && token.startsWith(TOKEN_PREFIX)) {
    		TokenAuthenticationHandler tokenAuthenticationHandler = new TokenAuthenticationHandler();
    		String subject = tokenAuthenticationHandler.getSubjectFromToken(token.replace(TOKEN_PREFIX, ""));
    		if(StringUtils.isNotBlank(subject)) {
    			SecurityContextHolder.getContext().setAuthentication(new JWTAuthenticationToken(subject));
    		}
    	}
    	filterChain.doFilter(request,response);
    }

}
