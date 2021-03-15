package com.mos.eboot.api.platform.controller;

import com.mos.eboot.api.config.UserUtil;
import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.shiro.entity.IUser;
import com.mos.eboot.tools.shiro.utils.PrincipalUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小尘哥
 * @date 2018/5/7 11:07
 * @description
 */
@RestController
public class DemoController extends BaseController{

	@Resource
	private ISysUserService sysUserService;

	@RequestMapping("demo/test1")
	public String test1(){
		IUser user = PrincipalUtils.getCurrentUser();
		String string = "this is test111111111";
		System.out.println(string);
		return string;
	}

	@RequestMapping("mine/demo/test2")
	public String test2(){
		SysUser user = UserUtil.getCurrentUser();
		String string = "this is test2222222";
		System.out.println(string);
		return string;
	}

	/*@PostMapping("/login")
	public ResultModel<String> login(@RequestParam("username") String username,
							 @RequestParam("password") String password) {
		SysUser user = sysUserService.getByUsername(username);
		if (user == null){
			throw new NormalException("用户名不正确");
		}
		String md5PWD = new PasswordHelper().encrypt(password);
		if (md5PWD.equals(user.getPassword())) {
			return ResultModel.defaultSuccess(JWTUtil.sign(username, md5PWD));
		} else {
			throw new UnauthorizedException();
		}
	}*/
}
