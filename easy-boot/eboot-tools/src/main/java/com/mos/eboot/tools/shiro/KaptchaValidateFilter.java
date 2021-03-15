package com.mos.eboot.tools.shiro;

import com.google.code.kaptcha.Constants;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.FastJsonUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author 小尘哥
 */
public class KaptchaValidateFilter extends AccessControlFilter{
	
	private Boolean kaptchaEnabled = Boolean.TRUE;
	
	public static final String DEFAULT_KAPTCHA_PARAM = "kaptcha";
	
	private String kaptchaParam = DEFAULT_KAPTCHA_PARAM;
	
	private String failureKeyAttribute = "shiroLoginFailure";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		request.setAttribute("kaptchaEnabled", kaptchaEnabled.booleanValue());
		
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		
		if(Boolean.FALSE.equals(kaptchaEnabled) || !HttpMethod.POST.toString().equalsIgnoreCase(httpServletRequest.getMethod())) {
			return true;
		}
		
		HttpSession session = httpServletRequest.getSession(false);
		
		if(session == null) {
			return false;
		}
		
		String kaptcha = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		if(!StringUtils.hasText(kaptcha)){
			return false;
		}
		
		session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
		
		return kaptcha.equalsIgnoreCase(getKaptcha(request));
		
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setCharacterEncoding("utf-8");
		if ("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"))) {
			ResultModel rm = new ResultModel(ResultStatus.FAIL.getCode(), "验证码输入不正确");
			PrintWriter out = null;
			try {
				out = httpServletResponse.getWriter();
				out.println(FastJsonUtils.toJSONString(rm));
				out.flush();
			}finally{
				if(out != null){
					out.close();
				}
			}
		}
		request.setAttribute(failureKeyAttribute, "验证码输入不正确");
		return true;
	}
	
	protected String getKaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getKaptchaParam());
    }

	public void setKaptchaEnabled(Boolean kaptchaEnabled) {
		this.kaptchaEnabled = kaptchaEnabled;
	}

	public String getKaptchaParam() {
		return kaptchaParam;
	}

	public void setKaptchaParam(String kaptchaParam) {
		this.kaptchaParam = kaptchaParam;
	}

	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}

}
