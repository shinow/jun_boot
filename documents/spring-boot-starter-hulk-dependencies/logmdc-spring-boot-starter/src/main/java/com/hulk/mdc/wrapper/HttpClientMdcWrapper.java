package com.hulk.mdc.wrapper;


import com.hulk.mdc.interceptor.HttpClientTraceIdInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * HttpClient工具类
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/26 15:42
 */
public class HttpClientMdcWrapper {
    private static CloseableHttpClient httpClient = HttpClientBuilder.create()
            .addInterceptorFirst(new HttpClientTraceIdInterceptor())
            .build();

    public static CloseableHttpClient newMdcInstance(){
        return httpClient;
    }
}
