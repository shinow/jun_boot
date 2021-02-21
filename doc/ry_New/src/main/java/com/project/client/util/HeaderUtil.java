package com.project.client.util;

import com.project.client.config.CommandConfig;
import com.project.client.domain.ReqComm;
import com.project.client.domain.Request;
import com.project.common.config.Global;
import com.project.common.json.JSON;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * tcp自定义通信协议解析工具
 */
public class HeaderUtil {
    public static final int DW_AUTH = 1548566063;
    private int authBufer;
    private byte vertion = 2;

    private byte type = 1;

    private byte type1 = 2;

    private byte type2 = 0;

    private byte type3 = 0;

    private Integer commandType = 0;

    public Request getMessageMapFromHeader(Object header) throws Exception {
        Request request = new Request();
        ByteBuffer headBf = ByteBuffer.wrap(header.toString().getBytes());
        headBf.order(ByteOrder.LITTLE_ENDIAN);
        if (headBf.limit() < 13) {
            return request;
        }
        //前四个字节转换成int
        this.authBufer = headBf.getInt();
        if (1548566063 != this.authBufer) {
            return request;
        }
        //依次向后每次读一个字节
        this.vertion = headBf.get();
        this.type = headBf.get();
        this.type1 = headBf.get();
        this.type2 = headBf.get();
        this.type3 = headBf.get();
        this.commandType = headBf.getInt();
        byte[] m = new byte[headBf.limit() - 13];
        headBf.get(m);
        Class messageType = CommandConfig.getType(this.commandType);
        if (messageType == null) {
            return request;
        }
        request = JSON.unmarshal(m,Request.class);
        request.setMessageType(messageType);
        request.setCommandType(this.commandType);
        request.setOK(true);
        return request;
    }

    public byte[] getReq(byte[] data,int commandType){
        byte[] src = new byte[data.length+13];
        System.arraycopy(int2Bytes(DW_AUTH),0,src,0,4);
        System.arraycopy(new byte[]{vertion},0,src,4,1);
        System.arraycopy(new byte[]{type},0,src,5,1);
        System.arraycopy(new byte[]{type1},0,src,6,1);
        System.arraycopy(new byte[]{type2},0,src,7,1);
        System.arraycopy(new byte[]{type3},0,src,8,1);
        System.arraycopy(int2Bytes(commandType),0,src,9,4);
        System.arraycopy(data,0,src,13,data.length);
        return src;
    }

    public String getReq(Request request) throws Exception{
        String str = JSON.marshal(request);
        byte[] data = str.getBytes();
        return new String(getReq(data,request.getCommandType()),Global.getCharacterSet());
    }
    private byte[] int2Bytes( int value ) {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
}
