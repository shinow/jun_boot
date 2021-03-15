package com.mos.eboot.admin.report.controller;

import com.mos.eboot.admin.platform.api.ISysUserService;
import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.vo.ChartVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小尘哥
 * @date 2018/5/3 9:34
 * @description
 */
@Controller
@RequestMapping("report/platform")
public class PlatformReportController extends BaseController {

	@Resource
	private ISysUserService sysUserService;

	@RequestMapping("user-report")
	public String userReport(){
		return "report/platform/user-report";
	}

	@ResponseBody
	@RequestMapping("login-count")
	public ResultModel<List<ChartVO>> loginCount(){
		return sysUserService.loginCount();
	}
}
