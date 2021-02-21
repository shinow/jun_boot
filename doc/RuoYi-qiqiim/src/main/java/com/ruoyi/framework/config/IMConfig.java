package com.ruoyi.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruoyi.project.im.rebot.proxy.RebotProxy;
import com.ruoyi.project.im.rebot.proxy.impl.MLRebotProxy;
import com.ruoyi.project.im.rebot.proxy.impl.TLRebotProxy;
import com.ruoyi.project.im.server.ImServer;
import com.ruoyi.project.im.server.ImWebsocketServer;

@Configuration
public class IMConfig {

	@Bean(initMethod="init",destroyMethod="destroy")
	public ImServer ImServer() {
		ImServer server= new ImServer();
		server.setPort(2000);
		return server;
	}
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public ImWebsocketServer ImWebsocketServer() {
		ImWebsocketServer server=new ImWebsocketServer();
		server.setPort(2048);
		return server;
	}
 
	@Bean
	public RebotProxy RebotProxy() {
		//茉莉机器人
		MLRebotProxy robot= new MLRebotProxy();
		robot.setApiUrl("");
		robot.setKey("");
		robot.setSecret("");
		
		//图灵机器人 
//		TLRebotProxy robot=new TLRebotProxy();
//		
//		robot.setApiUrl("");
//		robot.setKey("");
//		robot.setSecret("");
		
		return robot;
	}
	
}
