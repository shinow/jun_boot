package com.hulk.mdc.util;

import java.util.UUID;

/**
 * traceId生成工具类
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/18 15:19
 */
public class TraceIdUtil {
    public static String getTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
