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
 * 处理多sheet 页面
 */
@RequiredArgsConstructor
public class ManySheetWriteHandler extends AbstractSheetWriteHandler {
	private final ExcelProperties configProperties;

	/**
	 * 当且仅当List不为空且List中的元素也是List 才返回true
	 *
	 * @param obj 返回对象
	 * @return
	 */
	@Override
	public boolean support(Object obj) {
		if (obj instanceof List) {
			List objList = (List) obj;
			return !objList.isEmpty()&&objList.get(0) instanceof List;
		} else {
			throw new ExcelException("@RespExcel 返回值必须为List类型");
		}
	}

	@Override
	@SneakyThrows
	public void write(Object obj, HttpServletResponse response, RespExcel respExcel) {
		List objList = (List) obj;
		List eleList = (List) objList.get(0);

		ExcelWriter excelWriter = getExcelWriter(response, respExcel, eleList, configProperties.getTemplatePath());

		String[] sheets = respExcel.sheet();
		for (int i = 0; i < sheets.length; i++) {
			//创建sheet
			WriteSheet sheet;
			if (StringUtils.hasText(respExcel.template())) {
				sheet = EasyExcel.writerSheet(i).build();
			} else {
				sheet = EasyExcel.writerSheet(i, sheets[i]).build();
			}

			// 写入sheet
			excelWriter.write((List) objList.get(i), sheet);
		}
		excelWriter.finish();
	}
}
