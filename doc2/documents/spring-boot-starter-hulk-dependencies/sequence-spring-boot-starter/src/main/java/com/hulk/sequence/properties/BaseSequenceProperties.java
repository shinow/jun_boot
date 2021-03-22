package com.hulk.sequence.properties;

import com.hulk.sequence.util.IdWorker;
import lombok.Data;

/**
 * @author hulk
 * @date 2019-05-26
 * <p>
 * 发号器通用属性
 */
@Data
class BaseSequenceProperties {
	/**
	 * 获取range步长[可选，默认：3]
	 */
	private int step = 1;

	/**
	 * 序列号分配起始值[可选：默认：1]
	 */
	private long stepStart = IdWorker.getId();

	/**
	 * 业务名称
	 */
	private String bizName = "seq";
}
