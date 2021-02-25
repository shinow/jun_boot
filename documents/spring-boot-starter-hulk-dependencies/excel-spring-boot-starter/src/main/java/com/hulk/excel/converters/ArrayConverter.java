package com.hulk.excel.converters;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.StringUtils;

/**
 * 数组转换
 *
 * @author hulk
 */
public enum ArrayConverter implements Converter<Object[]> {
	/**
	 * 实例
	 */
	INSTANCE(DefaultConversionService.getSharedInstance());

	private final ConversionService conversionService;

	ArrayConverter(ConversionService sharedInstance) {
		this.conversionService = sharedInstance;
	}

	@Override
	public Class<?> supportJavaTypeKey() {
		return Object[].class;
	}

	@Override
	public CellDataTypeEnum supportExcelTypeKey() {
		return CellDataTypeEnum.STRING;
	}

	@Override
	public Object[] convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
		String[] value = StringUtils.delimitedListToStringArray(cellData.getStringValue(), ",");
		return (Object[]) conversionService.convert(value, TypeDescriptor.valueOf(String[].class), new TypeDescriptor(contentProperty.getField()));
	}

	@Override
	public CellData<String> convertToExcelData(Object[] value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
		return new CellData<>(StringUtils.arrayToCommaDelimitedString(value));
	}

}
