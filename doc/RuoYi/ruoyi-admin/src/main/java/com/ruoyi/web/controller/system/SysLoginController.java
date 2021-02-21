package com.smartpiggy.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smartpiggy.common.core.controller.BaseController;
import com.smartpiggy.common.core.domain.AjaxResult;
import com.smartpiggy.common.utils.ServletUtils;
import com.smartpiggy.common.utils.StringUtils;
 

/**
 * 登录验证
 * 
 * @author smartpiggy
 */

@Controller
public class SysLoginController extends BaseController
{
	@CrossOrigin(origins = "*",maxAge = 3600)
	@GetMapping("/login")
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
	
		String username=request.getParameter("username");
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, "admin123", false);
		try {
			subject.login(token);
			return success();
		} catch (AuthenticationException e) {
			String msg = "用户或密码错误";
			if (StringUtils.isNotEmpty(e.getMessage())) {
				msg = e.getMessage();
			}
			return error(msg);
		}

	}
	

//    @PostMapping("/login")
//    @ResponseBody
//    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
//    {
//    	
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
//        Subject subject = SecurityUtils.getSubject();
//        try
//        {
//            subject.login(token);
//            return success();
//        }
//        catch (AuthenticationException e)
//        {
//            String msg = "用户或密码错误";
//            if (StringUtils.isNotEmpty(e.getMessage()))
//            {
//                msg = e.getMessage();
//            }
//            return error(msg);
//        }
//    }


    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
