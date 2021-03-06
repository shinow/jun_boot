/**
 ***************************************************************************************
 *  @Author     1044053532@qq.com   
 *  @License    http://www.apache.org/licenses/LICENSE-2.0
 ***************************************************************************************
 */
package com.ruoyi.project.im.server;

import static io.netty.buffer.Unpooled.wrappedBuffer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.ruoyi.project.im.constant.IMConstants;
import com.ruoyi.project.im.server.connertor.impl.ImConnertorImpl;
import com.ruoyi.project.im.server.model.proto.MessageProto;
import com.ruoyi.project.im.server.proxy.MessageProxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


public class ImWebsocketServer  {

    private final static Logger log = LoggerFactory.getLogger(ImWebsocketServer.class);
    
    private ProtobufDecoder decoder = new ProtobufDecoder(MessageProto.Model.getDefaultInstance());
    @Autowired
    private MessageProxy proxy ;
    @Autowired
    private ImConnertorImpl connertor; 
    private int port;
 
    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    
    public void init() throws Exception {
        log.info("start qiqiim websocketserver ...");

        // Server ????????????
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
	    		ChannelPipeline pipeline = ch.pipeline();
	    		
	    		 // HTTP????????????????????????
	            pipeline.addLast(new HttpServerCodec());
	            // ???????????????????????????????????????FullHttpRequest??????FullHttpResponse???
	            // ?????????HTTP?????????????????????HTTP?????????????????????????????????HttpRequest/HttpResponse,HttpContent,LastHttpContent
	            pipeline.addLast(new HttpObjectAggregator(IMConstants.ImserverConfig.MAX_AGGREGATED_CONTENT_LENGTH));
	            // ?????????????????????????????????????????????1G???????????????????????????????????????????????????jvm?????????; ??????????????????????????????????????????
	            pipeline.addLast(new ChunkedWriteHandler());
	            // WebSocket????????????
	            pipeline.addLast(new WebSocketServerCompressionHandler());
	            // ?????????????????????
	            pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true, IMConstants.ImserverConfig.MAX_FRAME_LENGTH));
	            // ???????????????
	            pipeline.addLast(new MessageToMessageDecoder<WebSocketFrame>() {
	                @Override
	                protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) throws Exception {
	                    ByteBuf buf = ((BinaryWebSocketFrame) frame).content();
	                    objs.add(buf);
	                    buf.retain();
	                }
	            });
	            // ???????????????
	            pipeline.addLast(new MessageToMessageEncoder<MessageLiteOrBuilder>() {
	                @Override
	                protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
	                    ByteBuf result = null;
	                    if (msg instanceof MessageLite) {
	                        result = wrappedBuffer(((MessageLite) msg).toByteArray());
	                    }
	                    if (msg instanceof MessageLite.Builder) {
	                        result = wrappedBuffer(((MessageLite.Builder) msg).build().toByteArray());
	                    }
	                    // ?????????????????????websocket????????????????????????????????????????????????protobuf???????????????
	                    WebSocketFrame frame = new BinaryWebSocketFrame(result);
	                    out.add(frame);
	                }
	            });
	            // ????????????????????????Protobuf?????????????????????CommonProtocol??????
	            pipeline.addLast(decoder);
	            pipeline.addLast(new IdleStateHandler(IMConstants.ImserverConfig.READ_IDLE_TIME,IMConstants.ImserverConfig.WRITE_IDLE_TIME,0));
	            // ???????????????
	            pipeline.addLast(new ImWebSocketServerHandler(proxy,connertor));
	    		 
            }
        });
        
        // ????????????
    	bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        // ?????????????????????????????????
        log.info("start qiqiim websocketserver at port[" + port + "].");
        ChannelFuture future = bootstrap.bind(port).sync();
    	channel = future.channel();
        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    log.info("websocketserver have success bind to " + port);
                } else {
                    log.error("websocketserver fail bind to " + port);
                }
            }
        });
       // future.channel().closeFuture().syncUninterruptibly();
    }

    public void destroy() {
        log.info("destroy qiqiim websocketserver ...");
        // ?????????????????????
        if (channel != null) {
			channel.close();
		}
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        log.info("destroy qiqiim webscoketserver complate.");
    }
    
 
    

    public void setPort(int port) {
        this.port = port;
    }

	public void setProxy(MessageProxy proxy) {
		this.proxy = proxy;
	}
 

    public void setConnertor(ImConnertorImpl connertor) {
		this.connertor = connertor;
	}
    
    
}
