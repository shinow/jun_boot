package com.hulk.excel.expression;

import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * @author hulk
 * @date 2020/3/29
 */
public class NameSpelExpressionResolver implements NameResolver {

	/**
	 * 参数发现器
	 */
	private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
	/**
	 * Express语法解析器
	 */
	private static final ExpressionParser PARSER = new SpelExpressionParser();

	@Override
	public String doDetermineName(Object[] args, Method method, String key) {

		if (!key.contains("#")) {
			return key;
		}

		EvaluationContext context = new MethodBasedEvaluationContext(null, method, args, NAME_DISCOVERER);
		final Object value = PARSER.parseExpression(key).getValue(context);
		return value == null ? null : value.toString();
	}
}
