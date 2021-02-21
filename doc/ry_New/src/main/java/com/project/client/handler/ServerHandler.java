package com.project.client.handler;

import com.project.client.dispatcher.MessageDispatcher;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerHandler extends IoHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);
    @Autowired
    public MessageDispatcher dispatcher;
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        this.dispatcher.dispatcher(session,message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("消息处理时发生异常："+cause.getMessage());
    }
}
