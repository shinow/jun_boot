package com.project.client.test;

import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import com.project.client.domain.ReqComm;
import com.project.client.domain.Request;
import com.project.client.util.HeaderUtil;
import com.project.common.config.Global;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
/**
 * @说明 Mina TCP客户端
 * @author 崔素强
 * @version 1.0
 * @since
 */
public class MinaTcpClient extends IoHandlerAdapter {
    private IoConnector connector;
    private static IoSession session;
    public MinaTcpClient() {
        connector = new NioSocketConnector();
        connector.getFilterChain().addFirst("protocolCodecFilter",getProtocolCodecFilter());
        connector.setHandler(this);
        ConnectFuture connFuture = connector.connect(new InetSocketAddress("192.168.10.63", 8885));
        connFuture.awaitUninterruptibly();
        session = connFuture.getSession();
        if(session.isConnected()){
            System.out.println("TCP 客户端启动");
        }
    }
    public static void main(String[] args) throws Exception {
        HeaderUtil headerUtil = new HeaderUtil();
        MinaTcpClient client = new MinaTcpClient();
        Request request = new Request();
        ReqComm comm = new ReqComm();
        comm.setUsercode("test001");
        comm.setToken("aaa");
        comm.setRoleid("ccc");
        comm.setMachine_code("1234");
        comm.setClient_version("4.10.0");
        comm.setClient_ip("192.168.10.1");
        comm.setClienttype(1);
        request.setCommon(comm);
        //测试1号接口
        request.setCommandType(1);
        String str = headerUtil.getReq(request);
//        str = str.replace("\"messageType\" : null,  \"commandType\" : 18,","");
//        str = str.replace(",  \"ok\" : false","");
        session.write(str);
        // 关闭会话，待所有线程处理结束后
        Thread.sleep(30000);
        client.connector.dispose(true);
    }
    public ProtocolCodecFilter getProtocolCodecFilter(){
        TextLineCodecFactory factory = new TextLineCodecFactory(Charset.forName(Global.getCharacterSet()));
        factory.setDecoderMaxLineLength(102400);
        factory.setEncoderMaxLineLength(102400);
        return new ProtocolCodecFilter(factory);
    }
    @Override
    public void messageReceived(IoSession iosession, Object message)
            throws Exception {
        System.out.println("客户端收到消息:" + URLDecoder.decode(message.toString(),"utf-8"));
    }
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("客户端异常");
        super.exceptionCaught(session, cause);
    }
    @Override
    public void messageSent(IoSession iosession, Object obj) throws Exception {
        System.out.println("客户端消息发送" + obj);
        super.messageSent(iosession, obj);
    }
    @Override
    public void sessionClosed(IoSession iosession) throws Exception {
        System.out.println("客户端会话关闭");
        super.sessionClosed(iosession);
    }
    @Override
    public void sessionCreated(IoSession iosession) throws Exception {
        System.out.println("客户端会话创建");
        super.sessionCreated(iosession);
    }
    @Override
    public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
            throws Exception {
        System.out.println("客户端会话休眠");
        super.sessionIdle(iosession, idlestatus);
    }
    @Override
    public void sessionOpened(IoSession iosession) throws Exception {
        System.out.println("客户端会话打开");
        super.sessionOpened(iosession);
    }
}