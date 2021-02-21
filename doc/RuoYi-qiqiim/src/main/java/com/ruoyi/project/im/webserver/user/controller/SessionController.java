/**
 ***************************************************************************************
 *  @Author     1044053532@qq.com   
 *  @License    http://www.apache.org/licenses/LICENSE-2.0
 ***************************************************************************************
 */
package com.ruoyi.project.im.webserver.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.server.connertor.ImConnertor;
import com.ruoyi.project.im.server.group.ImChannelGroup;
import com.ruoyi.project.im.server.model.MessageWrapper;
import com.ruoyi.project.im.server.model.proto.MessageBodyProto;
import com.ruoyi.project.im.server.model.proto.MessageProto;
import com.ruoyi.project.im.server.session.SessionManager;
import com.ruoyi.project.im.webserver.base.controller.IMBaseController;

@Controller
@RequestMapping("/im/user/imuser")
public class SessionController extends IMBaseController{
	@Autowired
	private SessionManager sessionManager;
	@Autowired
	private ImConnertor connertor;
	    
	   
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> params,HttpServletRequest request){
		request.setAttribute("allsession", sessionManager.getSessions());
		return "user/userlist";
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/msgbroadcast", method = RequestMethod.POST)
	@ResponseBody
	public String broadcast(@RequestParam String msgContent,String session,HttpServletRequest request){
		 
		 MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
         builder.setCmd(IMConstants.CmdType.MESSAGE);
         builder.setSender("-1");
         builder.setMsgtype(IMConstants.ProtobufType.NOTIFY);
         MessageBodyProto.MessageBody.Builder  msg =  MessageBodyProto.MessageBody.newBuilder();
         msg.setContent(msgContent); 
         builder.setContent(msg.build().toByteString());
         if(StringUtils.isNotEmpty(session)){
        	 //推送到个人
        	 MessageWrapper  msgWrapper = new MessageWrapper(MessageWrapper.MessageProtocol.NOTIFY, session, null,builder);
        	 connertor.pushMessage(msgWrapper);
         }else{
        	 //推送到所有用户
        	 ImChannelGroup.broadcast(builder);
         }
	     return JSONArray.toJSONString(1);
    }
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/offline", method = RequestMethod.POST)
	@ResponseBody
	public String offlineUser(@RequestParam String msgContent,String session,HttpServletRequest request){
		 boolean result = false;
		 MessageProto.Model.Builder builder = MessageProto.Model.newBuilder();
         builder.setCmd(IMConstants.CmdType.MESSAGE);
         builder.setSender("-1");
         builder.setMsgtype(IMConstants.ProtobufType.NOTIFY);
         MessageBodyProto.MessageBody.Builder  msg =  MessageBodyProto.MessageBody.newBuilder();
         if(StringUtils.isNotEmpty(msgContent)){
        	  msg.setContent(msgContent); 
         }else{
        	  msg.setContent("已被系统强制下线"); 
         } 
         builder.setContent(msg.build().toByteString());
         if(StringUtils.isNotEmpty(session)){
        	 //推送到个人
        	 MessageWrapper  msgWrapper = new MessageWrapper(MessageWrapper.MessageProtocol.NOTIFY, session, null,builder);
        	 connertor.pushMessage(msgWrapper);
        	 connertor.close(session);
        	 result = true;
         }else{
        	 //广播下线消息，所有用户下线
        	 ImChannelGroup.broadcast(builder);
        	 ImChannelGroup.disconnect();
         }
	     return JSONArray.toJSONString(result);
    }
	

}


 