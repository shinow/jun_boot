package com.hulk.excel.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.hulk.excel.annotation.RespExcel;
import com.hulk.excel.config.ExcelProperties;
import com.hulk.excel.exception.ExcelException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hulk
 * @date 2020/3/29
 * <p>
 * 处理单sheet 页面
 */
@RequiredArgsConstructor
public class SingleSheetWriteHandler extends AbstractSheetWriteHandler {
	private final ExcelProperties configProperties;

	/**
	 * obj 是List 且list不为空同时list中的元素不是是List 才返回true
	 *
	 * @param obj 返回对象
	 * @return
	 */
	@Override
	public boolean support(Object obj) {
		if (obj instanceof List) {
			List objList = (List) obj;
			return !objList.isEmpty()&&!(objList.get(0) instanceof List);
		} else {
			throw new ExcelException("@RespExcel 返回值必须为List类型");
		}
	}

	@Override
	@SneakyThrows
	public void write(Object obj, HttpServletResponse response, RespExcel respExcel) {
		List list = (List) obj;

		ExcelWriter excelWriter = getExcelWriter(response, respExcel, list, configProperties.getTemplatePath());
		// 有模板则不指定sheet名
		WriteSheet sheet = StringUtils.hasText(respExcel.template()) ?
			EasyExcel.writerSheet().build() : EasyExcel.writerSheet(respExcel.sheet()[0]).build();

		excelWriter.write(list, sheet);
		excelWriter.finish();
	}
}
