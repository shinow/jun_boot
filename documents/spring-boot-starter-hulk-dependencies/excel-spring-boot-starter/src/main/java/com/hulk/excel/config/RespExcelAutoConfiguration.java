package com.hulk.excel.config;

import com.hulk.excel.aop.DynamicNameAspect;
import com.hulk.excel.aop.RespExcelReturnValueHandler;
import com.hulk.excel.handler.ManySheetWriteHandler;
import com.hulk.excel.handler.SheetWriteHandler;
import com.hulk.excel.handler.SingleSheetWriteHandler;
import com.hulk.excel.expression.NameResolver;
import com.hulk.excel.expression.NameSpelExpressionResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hulk
 * @date 2020/3/29
 * <p>
 * 配置初始化
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(ExcelProperties.class)
public class RespExcelAutoConfiguration implements ApplicationContextAware, InitializingBean {
	private ApplicationContext applicationContext;
	private final List<SheetWriteHandler> sheetWriteHandlerList;


	@Bean
	@ConditionalOnMissingBean
	public NameResolver nameResolver() {
		return new NameSpelExpressionResolver();
	}

	@Bean
	@ConditionalOnBean(NameResolver.class)
	@ConditionalOnMissingBean
	public DynamicNameAspect dynamicNameAspect (NameResolver nameResolver){
		DynamicNameAspect aspect = new DynamicNameAspect(nameResolver);
		return  aspect;
	}

	@Bean
	@ConditionalOnMissingBean
	public SingleSheetWriteHandler singleSheetWriteHandler (ExcelProperties properties){
		SingleSheetWriteHandler sheetWriteHandler = new SingleSheetWriteHandler(properties);
		return  sheetWriteHandler;
	}

	@Bean
	@ConditionalOnMissingBean
	public ManySheetWriteHandler manySheetWriteHandler (ExcelProperties properties){
		ManySheetWriteHandler sheetWriteHandler = new ManySheetWriteHandler(properties);
		return  sheetWriteHandler;
	}

	@Override
	public void afterPropertiesSet() {
		RequestMappingHandlerAdapter handlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
		List<HandlerMethodReturnValueHandler> returnValueHandlers = handlerAdapter.getReturnValueHandlers();

		List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>();
		newHandlers.add(new RespExcelReturnValueHandler(sheetWriteHandlerList));
		assert returnValueHandlers != null;
		newHandlers.addAll(returnValueHandlers);
		handlerAdapter.setReturnValueHandlers(newHandlers);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
