

package com.hulk.mdc.interceptor;


import com.hulk.mdc.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author hulk
 * @date 2018/9/14
 */
@Slf4j
public class FeignMdcInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate)  {
		String traceId = MDC.get(Constants.TRACE_ID);
		if (traceId != null) {
			//添加请求体
			requestTemplate.header(Constants.TRACE_ID, traceId);
		}

	}
}
