package com.hulk.sequence.builder;


import com.hulk.sequence.sequence.Sequence;

/**
 * 序列号生成器构建者
 * @author hulk on 2018/5/30.
 */
public interface SeqBuilder {

	/**
	 * 构建一个序列号生成器
	 *
	 * @return 序列号生成器
	 */
	Sequence build();

}
