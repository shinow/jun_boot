package com.hulk.excel.aop;

import com.hulk.excel.annotation.RespExcel;
import com.hulk.excel.expression.NameResolver;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

/**
 * @author hulk
 * @date 2020/3/29
 */
@Aspect
@RequiredArgsConstructor
public class DynamicNameAspect {
	public static final String EXCEL_NAME_KEY = "__EXCEL_NAME_KEY__";
	private final NameResolver processor;

	@Before("@annotation(excel)")
	public void around(JoinPoint point, RespExcel excel) {
		MethodSignature ms = (MethodSignature) point.getSignature();
		String name = processor.doDetermineName(point.getArgs(), ms.getMethod(), excel.name());
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		Objects.requireNonNull(requestAttributes).setAttribute(EXCEL_NAME_KEY, name, RequestAttributes.SCOPE_REQUEST);
	}
}
