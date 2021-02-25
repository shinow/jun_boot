package com.hulk.excel.aop;


import com.hulk.excel.annotation.RespExcel;
import com.hulk.excel.handler.SheetWriteHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 处理@RespExcel 返回值
 *
 * @author hulk
 */
@Slf4j
@RequiredArgsConstructor
public class RespExcelReturnValueHandler implements HandlerMethodReturnValueHandler {
	private final List<SheetWriteHandler> sheetWriteHandlerList;


	/**
	 * 只处理@RespExcel 声明的方法
	 *
	 * @param parameter 方法签名
	 * @return 是否处理
	 */
	@Override
	public boolean supportsReturnType(MethodParameter parameter) {
		return parameter.getMethodAnnotation(RespExcel.class) != null;
	}

	/**
	 * 处理逻辑
	 *
	 * @param o                返回参数
	 * @param parameter        方法签名
	 * @param mavContainer     上下文容器
	 * @param nativeWebRequest 上下文
	 * @throws Exception 处理异常
	 */
	@Override
	public void handleReturnValue(Object o, MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest) throws Exception {
		/* check */
		HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
		Assert.state(response != null, "No HttpServletResponse");
		RespExcel respExcel = parameter.getMethodAnnotation(RespExcel.class);
		Assert.state(respExcel != null, "No @RespExcel");
		mavContainer.setRequestHandled(true);

		sheetWriteHandlerList.forEach(handler -> {
			handler.export(o, response, respExcel);
		});
	}
}
