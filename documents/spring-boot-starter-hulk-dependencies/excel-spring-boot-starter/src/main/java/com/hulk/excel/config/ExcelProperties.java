package com.hulk.excel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hulk
 * @date 2020/3/29
 */
@Data
@ConfigurationProperties(prefix = ExcelProperties.PREFIX)
public class ExcelProperties {
	static final String PREFIX = "excel";

	/**
	 * 模板路径
	 */
	private String templatePath = "excel";
}
