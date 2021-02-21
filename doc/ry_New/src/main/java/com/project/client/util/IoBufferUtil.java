package com.project.client.util;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBufferUtil {

    public static String ioBufferToString(IoBuffer ioBuffer){
        byte[] bytes = new byte[ioBuffer.limit()];
        ioBuffer.get(bytes);
        return new String(bytes);
    }
    public static IoBuffer stringToIoBuffer(String string){
        return IoBuffer.wrap(string.getBytes());
    }
}
