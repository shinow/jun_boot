package com.ruoyi.project.im.webserver.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.webserver.base.controller.IMBaseController;
import com.ruoyi.project.im.webserver.user.model.UserMessageEntity;
import com.ruoyi.project.im.webserver.user.service.UserMessageService;
import com.ruoyi.project.im.webserver.util.Query;
 
/**
 * 
 * 
 * @author qiqiim
 * @email 1044053532@qq.com
 * @date 2017-11-23 10:47:47
 */
@Controller
@RequestMapping("/im/usermessage")
public class UserMessageController extends IMBaseController{
	@Autowired
	private UserMessageService userMessageServiceImpl;
	
	/**
	 * 页面
	 */
	@RequestMapping("/page")
	public String page(@RequestParam Map<String, Object> params){
		return "user/usermessage";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<UserMessageEntity> userMessageList = userMessageServiceImpl.queryList(query);
		int total = userMessageServiceImpl.queryTotal(query);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",total,userMessageList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping(value="/info/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object info(@PathVariable("id") Long id){
		UserMessageEntity userMessage = userMessageServiceImpl.queryObject(id);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userMessage);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody UserMessageEntity userMessage){
		userMessageServiceImpl.save(userMessage);
		return putMsgToJsonString(IMConstants.WebSite.SUCCESS,"",0,userMessage);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody UserMessageEntity userMessage){
		int result = userMessageServiceImpl.update(userMessage);
		return putMsgToJsonString(result,"",0,"");
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestBody Long[] ids){
		int result = userMessageServiceImpl.deleteBatch(ids);
		return putMsgToJsonString(result,"",0,"");
	}
	
}
