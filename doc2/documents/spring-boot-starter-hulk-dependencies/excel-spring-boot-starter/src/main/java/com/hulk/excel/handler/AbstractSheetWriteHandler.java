package com.hulk.excel.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.hulk.excel.annotation.RespExcel;
import com.hulk.excel.aop.DynamicNameAspect;
import com.hulk.excel.converters.ArrayConverter;
import com.hulk.excel.converters.CollectionConverter;
import com.hulk.excel.converters.LocalDateStringConverter;
import com.hulk.excel.converters.LocalDateTimeStringConverter;
import com.hulk.excel.exception.ExcelException;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author hulk
 * @date 2020/3/31
 */
public abstract class AbstractSheetWriteHandler implements SheetWriteHandler {

	@Override
	public void check(RespExcel respExcel) {
		if (!StringUtils.hasText(respExcel.name())) {
			throw new ExcelException("@RespExcel name 配置不合法");
		}

		if (respExcel.sheet().length == 0) {
			throw new ExcelException("@RespExcel sheet 配置不合法");
		}
	}

	@Override
	@SneakyThrows
	public void export(Object o, HttpServletResponse response, RespExcel respExcel) {
		if (support(o)) {
			check(respExcel);
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			String name = (String) Objects.requireNonNull(requestAttributes)
				.getAttribute(DynamicNameAspect.EXCEL_NAME_KEY, RequestAttributes.SCOPE_REQUEST);
			String fileName = String.format("%s%s", URLEncoder.encode(name, "UTF-8"), respExcel.suffix().getValue());
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
			write(o, response, respExcel);
		}
	}

	/**
	 * 通用的获取ExcelWriter方法
	 *
	 * @param response      HttpServletResponse
	 * @param respExcel RespExcel注解
	 * @param list          Excel数据
	 * @param templatePath  模板地址
	 * @return ExcelWriter
	 */
	@SneakyThrows
	public ExcelWriter getExcelWriter(HttpServletResponse response, RespExcel respExcel, List list, String templatePath) {
		ExcelWriterBuilder writerBuilder = EasyExcel.write(response.getOutputStream(), list.get(0).getClass())
			.registerConverter(LocalDateStringConverter.INSTANCE)
			.registerConverter(LocalDateTimeStringConverter.INSTANCE)
			.registerConverter(ArrayConverter.INSTANCE)
			.registerConverter(CollectionConverter.INSTANCE)
			.autoCloseStream(true)
			.excelType(respExcel.suffix())
			.inMemory(respExcel.inMemory());

		if (StringUtils.hasText(respExcel.password())) {
			writerBuilder.password(respExcel.password());
		}

		if (respExcel.include().length != 0) {
			writerBuilder.includeColumnFiledNames(Arrays.asList(respExcel.include()));
		}

		if (respExcel.exclude().length != 0) {
			writerBuilder.excludeColumnFiledNames(Arrays.asList(respExcel.include()));
		}

		if (respExcel.writeHandler().length != 0) {
			for (Class<? extends WriteHandler> clazz : respExcel.writeHandler()) {
				writerBuilder.registerWriteHandler(clazz.newInstance());
			}
		}

		if (respExcel.converter().length != 0) {
			for (Class<? extends Converter> clazz : respExcel.converter()) {
				writerBuilder.registerConverter(BeanUtils.instantiateClass(clazz));
			}
		}

		if (StringUtils.hasText(respExcel.template())) {
			ClassPathResource classPathResource = new ClassPathResource(templatePath
				+ File.separator + respExcel.template());
			InputStream inputStream = classPathResource.getInputStream();
			writerBuilder.withTemplate(inputStream);
		}

		return writerBuilder.build();
	}
}
