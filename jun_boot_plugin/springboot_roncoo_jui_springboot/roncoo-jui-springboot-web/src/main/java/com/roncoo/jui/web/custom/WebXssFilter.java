package com.roncoo.jui.web.custom;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

/**
 * 防止XSS攻击的过滤器
 * 
 * @author Wujun
 */
@WebFilter(filterName = "webXssFilter", urlPatterns = "/admin/*")
public class WebXssFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {

	}
}

/**
 * 
 * @author Wujun
 */
class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * @param request
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				// 防xss攻击和过滤前后空格
				escapseValues[i] = HtmlUtils.htmlEscape(values[i]).trim();
			}
			return escapseValues;
		}
		return super.getParameterValues(name);
	}
}
