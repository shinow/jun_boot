package com.hulk.mdc.interceptor;


import com.hulk.mdc.constant.Constants;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


/**
 * RestTemplate添加 traceId拦截器
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/26 15:59
 */
public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {
    @SneakyThrows
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution)  {
        String traceId = MDC.get(Constants.TRACE_ID);
        if (traceId != null) {
            httpRequest.getHeaders().add(Constants.TRACE_ID, traceId);
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
