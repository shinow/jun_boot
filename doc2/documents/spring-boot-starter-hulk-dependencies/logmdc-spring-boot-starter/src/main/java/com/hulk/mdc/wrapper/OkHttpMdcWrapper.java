package com.hulk.mdc.wrapper;


import com.hulk.mdc.interceptor.OkHttpTraceIdInterceptor;
import okhttp3.OkHttpClient;

/**
 * OkHttp工具类
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/26 14:28
 */
public class OkHttpMdcWrapper {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new OkHttpTraceIdInterceptor())
            .build();

    public static OkHttpClient newMdcInstance(){
        return client;
    }
}
