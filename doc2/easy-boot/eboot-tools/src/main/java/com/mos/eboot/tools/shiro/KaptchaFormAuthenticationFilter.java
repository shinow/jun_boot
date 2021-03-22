package com.mos.eboot.tools.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.FastJsonUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;


/**
 * @author 小尘哥
 */
public class KaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private MessageSourceAccessor messageSourceAccessor;
	
	public KaptchaFormAuthenticationFilter(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setCharacterEncoding("UTF-8");
		if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
			PrintWriter out = null;
			try{
				out = httpServletResponse.getWriter();
		        out.println(FastJsonUtils.toJSONString(ResultModel.defaultSuccess(null)));
		        out.flush();
			}finally{
				if(out != null){
					out.close();
				}
			}
	        return false;
		}
		
		return super.onLoginSuccess(token, subject, request, response);
		
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setCharacterEncoding("utf-8");
		if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
			String className = e.getClass().getName();
			ResultModel rm = new ResultModel(ResultStatus.FAIL.getCode(), messageSourceAccessor.getMessage(className));
			PrintWriter out = null;
			try {
				out = httpServletResponse.getWriter();
				out.println(FastJsonUtils.toJSONString(rm));
				out.flush();
			} catch (IOException e1) {
				if(logger.isInfoEnabled()){
					e1.printStackTrace();
				}
			}finally{
				if(out != null){
					out.close();
				}
			}
			return false;
		}
		return super.onLoginFailure(token, e, request, response);
	}

	public MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}

	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

}
