package com.hulk.sequence.builder;


import com.hulk.sequence.sequence.Sequence;
import com.hulk.sequence.sequence.impl.SnowflakeSequence;

/**
 * 基于雪花算法，序列号生成器构建者
 * @author hulk on 2018/5/30.
 */
public class SnowflakeSeqBuilder implements SeqBuilder {

	/**
	 * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
	 */
	private long datacenterId = -1;


	public static SnowflakeSeqBuilder create() {
		SnowflakeSeqBuilder builder = new SnowflakeSeqBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		if(datacenterId<0||datacenterId>31){
			SnowflakeSequence sequence = new SnowflakeSequence();
			return sequence;
		}else {
			SnowflakeSequence sequence = new SnowflakeSequence(datacenterId);
			return sequence;
		}

	}

	public SnowflakeSeqBuilder datacenterId(long datacenterId) {
		this.datacenterId = datacenterId;
		return this;
	}



}
