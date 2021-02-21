package com.ruoyi.project.im.webserver.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.webserver.base.controller.IMBaseController;
import com.ruoyi.project.im.webserver.user.model.UserDepartmentEntity;
import com.ruoyi.project.im.webserver.user.service.UserDepartmentService;
import com.ruoyi.project.im.webserver.util.Query;
 
/**
 * 部门
 * 
 * @author qiqiim
 * @email 1044053532@qq.com
 * @date 2017-11-27 14:56:08
 */
@Controller
@RequestMapping("/im/userdepartment")
public class UserDepartmentController extends IMBaseController{
	@Autowired
	private UserDepartmentService userDepartmentServiceImpl;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "userdepartment";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam Map<String, Object> params){
	    Query query = new Query(params);
		List<UserDepartmentEntity> userDepartmentList = userDepartmentServiceImpl.queryList(query);
		int total = userDepartmentServiceImpl.queryTotal(query);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",total,userDepartmentList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping(value="/info/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object info(@PathVariable("id") Long id){
		UserDepartmentEntity userDepartment = userDepartmentServiceImpl.queryObject(id);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userDepartment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@ModelAttribute UserDepartmentEntity userDepartment){
		userDepartmentServiceImpl.save(userDepartment);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userDepartment);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@ModelAttribute UserDepartmentEntity userDepartment){
		int result = userDepartmentServiceImpl.update(userDepartment);
		return putMsgToJsonString(result,"",0,"");
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam Long[] ids){
		int result = userDepartmentServiceImpl.deleteBatch(ids);
		return putMsgToJsonString(result,"",0,"");
	}
	
}
