package com.project.client.filter;

import com.project.client.domain.Request;
import com.project.client.domain.Response;
import com.project.client.util.HeaderUtil;
import com.project.common.config.Global;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;

public class HeaderCheckFilter extends IoFilterAdapter {
    private static final Logger log = LoggerFactory.getLogger(HeaderCheckFilter.class);

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        Request request = new HeaderUtil().getMessageMapFromHeader(message);
        if (request.isOK()) {
            super.messageReceived(nextFilter, session, request);
        } else {
            Response response = Response.response(request.getCommandType(),100,"数据不合法.");
            session.write(URLEncoder.encode(response.writeBackStr(),"utf-8"));
        }
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        super.messageSent(nextFilter, session, writeRequest);
    }

    @Override
    public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception {
        log.error("头信息校验发生异常："+cause.getMessage());
    }

}
