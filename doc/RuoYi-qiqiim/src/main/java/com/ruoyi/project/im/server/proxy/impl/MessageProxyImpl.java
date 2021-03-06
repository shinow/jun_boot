/**
 ***************************************************************************************
 *  @Author     1044053532@qq.com   
 *  @License    http://www.apache.org/licenses/LICENSE-2.0
 ***************************************************************************************
 */
package com.ruoyi.project.im.server.proxy.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.rebot.proxy.RebotProxy;
import com.ruoyi.project.im.server.model.MessageWrapper;
import com.ruoyi.project.im.server.model.proto.MessageBodyProto;
import com.ruoyi.project.im.server.model.proto.MessageProto;
import com.ruoyi.project.im.server.proxy.MessageProxy;
import com.ruoyi.project.im.webserver.user.model.UserMessageEntity;
import com.ruoyi.project.im.webserver.user.service.UserMessageService;

@Component
public class MessageProxyImpl implements MessageProxy {
	private final static Logger log = LoggerFactory.getLogger(MessageProxyImpl.class);
	
	@Autowired
	private RebotProxy rebotProxy;
	@Autowired
	private UserMessageService userMessageServiceImpl;

    public MessageWrapper convertToMessageWrapper(String sessionId ,MessageProto.Model message) {
    	
        
        switch (message.getCmd()) {
			case IMConstants.CmdType.BIND:
				 try {
		            	return new MessageWrapper(MessageWrapper.MessageProtocol.CONNECT, message.getSender(), null,message);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				break;
			case IMConstants.CmdType.HEARTBEAT:
				try {
	                 return new MessageWrapper(MessageWrapper.MessageProtocol.HEART_BEAT, sessionId,null, null);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
				break;
			case IMConstants.CmdType.ONLINE:
				
				break;
			case IMConstants.CmdType.OFFLINE:
				
				break;
			case IMConstants.CmdType.MESSAGE:
					try {
						  MessageProto.Model.Builder  result = MessageProto.Model.newBuilder(message);
						  result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
						  result.setSender(sessionId);//???????????????sessionId
						  message =  MessageProto.Model.parseFrom(result.build().toByteArray());
						  //??????????????????????????????
						  if(StringUtils.isNotEmpty(message.getReceiver())){
							  //?????????????????????????????????
							  if(message.getReceiver().equals(IMConstants.ImserverConfig.REBOT_SESSIONID)){
								  MessageBodyProto.MessageBody  msg =  MessageBodyProto.MessageBody.parseFrom(message.getContent());
								  return  rebotProxy.botMessageReply(sessionId, msg.getContent());
							  }else{
								  return new MessageWrapper(MessageWrapper.MessageProtocol.REPLY, sessionId,message.getReceiver(), message);
							  }
						  }else if(StringUtils.isNotEmpty(message.getGroupId())){
							  return new MessageWrapper(MessageWrapper.MessageProtocol.GROUP, sessionId, null,message);
						  }else {
							  return new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null,message);
						  }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
				break;  
		} 
        return null;
    }

    
 
    @Override
	public void saveOnlineMessageToDB(MessageWrapper message) {
    	try{
    		UserMessageEntity  userMessage = convertMessageWrapperToBean(message);
    		if(userMessage!=null){
    			userMessage.setIsread(1);
            	userMessageServiceImpl.save(userMessage);
    		}
    	}catch(Exception e){
    		 log.error("MessageProxyImpl  user "+message.getSessionId()+" send msg to "+message.getReSessionId()+" error");
    		 throw new RuntimeException(e.getCause());
    	}
	}
    
    
    @Override
	public void saveOfflineMessageToDB(MessageWrapper message) {
    	try{
    		 
    		UserMessageEntity  userMessage = convertMessageWrapperToBean(message);
    		if(userMessage!=null){
    			userMessage.setIsread(0);
            	userMessageServiceImpl.save(userMessage);
    		}
    	}catch(Exception e){
    		 log.error("MessageProxyImpl  user "+message.getSessionId()+" send msg to "+message.getReSessionId()+" error");
    		 throw new RuntimeException(e.getCause());
    	} 
	}
    
    
    private UserMessageEntity convertMessageWrapperToBean(MessageWrapper message){
    	try{
    		//????????????????????????
    		if(!message.getSessionId().equals(IMConstants.ImserverConfig.REBOT_SESSIONID)){
    			MessageProto.Model  msg =  (MessageProto.Model)message.getBody();
            	MessageBodyProto.MessageBody  msgConten =  MessageBodyProto.MessageBody.parseFrom(msg.getContent());
            	UserMessageEntity  userMessage = new UserMessageEntity();
            	userMessage.setSenduser(message.getSessionId());
            	userMessage.setReceiveuser(message.getReSessionId());
            	userMessage.setContent(msgConten.getContent());
            	userMessage.setGroupid(msg.getGroupId());
            	userMessage.setCreatedate(msg.getTimeStamp());
            	userMessage.setIsread(1);
            	return userMessage;
    		}
    	}catch(Exception e){
    		 throw new RuntimeException(e.getCause());
    	} 
    	return null;
    }

	public void setRebotProxy(RebotProxy rebotProxy) {
		this.rebotProxy = rebotProxy;
	}



	@Override
	public MessageProto.Model getOnLineStateMsg(String sessionId) {
		MessageProto.Model.Builder  result = MessageProto.Model.newBuilder();
		result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.setSender(sessionId);//???????????????sessionId
		result.setCmd(IMConstants.CmdType.ONLINE);
		return result.build();
	}



	@Override
	public MessageProto.Model getOffLineStateMsg(String sessionId) {
		MessageProto.Model.Builder  result = MessageProto.Model.newBuilder();
		result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		result.setSender(sessionId);//???????????????sessionId
		result.setCmd(IMConstants.CmdType.OFFLINE);
		return result.build();
	}



	@Override
	public MessageWrapper getReConnectionStateMsg(String sessionId) {
		 MessageProto.Model.Builder  result = MessageProto.Model.newBuilder();
		 result.setTimeStamp(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		 result.setSender(sessionId);//???????????????sessionId
		 result.setCmd(IMConstants.CmdType.RECON);
		 return  new MessageWrapper(MessageWrapper.MessageProtocol.SEND, sessionId, null,result.build());
	}


	
    
    
}
