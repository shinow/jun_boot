package com.hulk.sequence.range.impl.name;

import com.hulk.sequence.range.BizName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author hulk
 * @date 2019-05-26
 * <p>
 * 根据时间重置bizname
 */
@NoArgsConstructor
@AllArgsConstructor
public class DateBizName implements BizName {
	private String bizName;

	/**
	 * 生成空间名称
	 */
	@Override
	public String create() {
		return bizName +LocalDate.now();
	}
}