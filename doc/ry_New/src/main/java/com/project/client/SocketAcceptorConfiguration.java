package com.project.client;

import com.project.client.dispatcher.MessageDispatcher;
import com.project.client.filter.HeaderCheckFilter;
import com.project.client.handler.ServerHandler;
import com.project.common.config.Global;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

@Configuration
public class SocketAcceptorConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SocketAcceptorConfiguration.class);
    @Bean
    public NioSocketAcceptor getNioSocketAcceptor(){
        NioSocketAcceptor acceptor = new NioSocketAcceptor(300);
        acceptor.setBacklog(Global.getMaxConns());
        //设置过滤器
        acceptor.getFilterChain().addFirst("executorFilter",getExecutorFilter());
        acceptor.getFilterChain().addAfter("executorFilter","protocolCodecFilter",getProtocolCodecFilter());
        acceptor.getFilterChain().addAfter("protocolCodecFilter","headerCheckFilter",getHeaderCheckFilter());
        //设置处理器
        acceptor.setHandler(getIoHandler());

        acceptor.getSessionConfig().setTcpNoDelay(true);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);
        acceptor.getSessionConfig().setReceiveBufferSize(1048576);
        acceptor.getSessionConfig().setReadBufferSize(1048576);
        acceptor.getSessionConfig().setKeepAlive(true);
        acceptor.getSessionConfig().setSoLinger(1);
        try {
            acceptor.bind(new InetSocketAddress(Global.getTcpPort()));
            log.info("------------------------tcp服务启动成功-----------------");
        } catch (IOException e) {
            log.error("tcp服务启动失败："+e.getMessage());
        }
        return acceptor;
    }

    @Bean
    public IoHandler getIoHandler(){
        return new ServerHandler();
    }
    @Bean
    public HeaderCheckFilter getHeaderCheckFilter(){
        return new HeaderCheckFilter();
    }
    @Bean
    public ExecutorFilter getExecutorFilter(){
        OrderedThreadPoolExecutor executor = new OrderedThreadPoolExecutor(8,32);
        return new ExecutorFilter(executor);
    }
    @Bean
    public ProtocolCodecFilter getProtocolCodecFilter(){
        TextLineCodecFactory factory = new TextLineCodecFactory(Charset.forName(Global.getCharacterSet()));
        factory.setDecoderMaxLineLength(102400);
        factory.setEncoderMaxLineLength(102400);
        return new ProtocolCodecFilter(factory);
    }
    @Bean
    public MessageDispatcher getMessageDispatcher(){
        return new MessageDispatcher();
    }
}
