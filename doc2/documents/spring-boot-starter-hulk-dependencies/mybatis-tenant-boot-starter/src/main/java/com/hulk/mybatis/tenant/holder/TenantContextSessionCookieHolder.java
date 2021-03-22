package com.hulk.mybatis.tenant.holder;


import com.hulk.mybatis.tenant.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hulk
 * @date 2018/10/4
 * 租户工具类
 */
public class TenantContextSessionCookieHolder implements TenantContextHolder {


private static final String TENANTID = "tenantId";

	/**
	 * TTL 设置租户ID
	 *
	 * @param tenantId
	 */
	@Override
	public void setTenantId(String tenantId) {
		HttpServletRequest req =  WebUtils.getRequest();
		req.getSession().setAttribute(TENANTID,tenantId);
		HttpServletResponse resp =  WebUtils.getResponse();
		WebUtils.setCookie(resp, TENANTID, tenantId,  60*60*24);
	}

	/**
	 * 获取TTL中的租户ID
	 *
	 * @return
	 */
	@Override
	public String getTenantId() {

		HttpServletRequest req =  WebUtils.getRequest();
	    String tenantId =	(String)req.getSession().getAttribute(TENANTID);
	    if(StringUtils.isEmpty(tenantId)){
			tenantId = WebUtils.getCookieVal(TENANTID);
		}

	    return tenantId;

	}
	@Override
	public void clear() {
		HttpServletRequest req =  WebUtils.getRequest();
		req.getSession().removeAttribute(TENANTID);
		HttpServletResponse resp =  WebUtils.getResponse();
		WebUtils.removeCookie(resp,TENANTID);
	}
}
