package com.hulk.sequence.properties;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hulk
 * @date 2019/5/26
 * <p>
 * 发号器DB配置属性
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "xsequence.db")
public class SequenceDbProperties extends BaseSequenceProperties {
	/**
	 * 表名称
	 */
	private String tableName = "xsequence";
	/**
	 * 重试次数
	 */
	private int retryTimes = 1;

}