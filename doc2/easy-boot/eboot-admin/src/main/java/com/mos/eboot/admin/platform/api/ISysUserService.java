package com.mos.eboot.admin.platform.api;


import com.baomidou.mybatisplus.plugins.Page;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.vo.ChartVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("boot-service")
public interface ISysUserService {

	/**
	 * 根据用户名获取user
	 * @param username
	 * @return
	 */
    @RequestMapping(value = "api/user/get-by-name",method = RequestMethod.GET)
    SysUser getByUsername(@RequestParam("username") String username);

	/**
	 * 新增/修改
	 * @param sysUser
	 * @return
	 */
    @RequestMapping(value = "api/user/save-or-update",method = RequestMethod.POST)
    ResultModel<String> saveOrUpdate(@RequestBody SysUser sysUser);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "api/user/del",method = RequestMethod.POST)
    ResultModel<String> deleteById(@RequestParam("id") String id);

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
    @RequestMapping(value = "api/user/query-page",method = RequestMethod.POST)
    ResultModel<Page<SysUser>> queryPage(Page<SysUser> page);

	/**
	 * getById
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "api/user/get-by-id",method = RequestMethod.GET)
    ResultModel<SysUser> getById(@RequestParam("id")String id);

	/**
	 * 登录统计分析
	 * @return
	 */
	@RequestMapping(value = "api/user/login-count",method = RequestMethod.GET)
	ResultModel<List<ChartVO>> loginCount();

	/**
	 * 全部用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "api/user/all",method = RequestMethod.POST)
	ResultModel<List<SysUser>> all(SysUser user);

}
