package com.hulk.mdc.wrapper;

import com.hulk.mdc.interceptor.RestTemplateTraceIdInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * RestTemplate工具类
 *
 * @author hulk
 * @version 1.0
 * @Date 2020/03/26 16:11
 */
public class RestTemplateMdcWrapper {
    private static RestTemplate restTemplate = new RestTemplate();



    public static RestTemplate newMdcInstance(){
        restTemplate.setInterceptors(Arrays.asList(new RestTemplateTraceIdInterceptor()));
        return restTemplate;
    }
}
