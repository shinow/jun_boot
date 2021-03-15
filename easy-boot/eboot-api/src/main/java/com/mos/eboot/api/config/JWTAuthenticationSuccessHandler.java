package com.mos.eboot.api.config;

import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.tools.result.ResultStatus;
import com.mos.eboot.tools.util.FastJsonUtils;
import com.mos.eboot.tools.util.IPUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author 小尘哥
 */
@Component
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Resource
	private ISysUserService sysUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		Object principal = authentication.getPrincipal();
		if (principal != null) {
			SysUser user = (SysUser) principal;
			response.setContentType("application/json");
			SysUser updateAccUser = new SysUser();
			updateAccUser.setId(user.getId());
			updateAccUser.setLastLoginTime(new Date());
			updateAccUser.setIp(IPUtil.getIp(request));
			sysUserService.saveOrUpdate(updateAccUser);
			response.getWriter().write(FastJsonUtils.toJSONString(new ResultModel<>(ResultStatus.SUCCESS, user)));
		}
	}

}
