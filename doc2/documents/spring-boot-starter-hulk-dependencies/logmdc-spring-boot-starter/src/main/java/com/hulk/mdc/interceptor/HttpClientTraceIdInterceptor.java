package com.hulk.mdc.interceptor;


import com.hulk.mdc.constant.Constants;

import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.MDC;



/**
 * 添加HttpClient拦截器
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/26 15:47
 */
public class HttpClientTraceIdInterceptor implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        String traceId = MDC.get(Constants.TRACE_ID);
        //当前线程调用中有traceId，则将该traceId进行透传
        if (traceId != null) {
            //添加请求体
            httpRequest.addHeader(Constants.TRACE_ID, traceId);
        }
    }
}
