package com.hulk.sequence.builder;


import com.hulk.sequence.range.BizName;
import com.hulk.sequence.range.impl.redis.RedisSeqRangeMgr;
import com.hulk.sequence.sequence.Sequence;
import com.hulk.sequence.sequence.impl.DefaultRangeSequence;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 基于redis取步长，序列号生成器构建者
 *
 * @author hulk on 2018/5/30.
 */
public class RedisSeqBuilder implements SeqBuilder {


	/**
	 * 业务名称[必选]
	 */
	private BizName bizName;

	/**
	 * 获取range步长[可选，默认：1]
	 */
	private int step = 1;

	/**
	 * 序列号分配起始值[可选：默认：0]
	 */
	private long stepStart = 0;

	private RedisTemplate seqRedisTemplate;

	public static RedisSeqBuilder create() {
		RedisSeqBuilder builder = new RedisSeqBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		//利用Redis获取区间管理器
		RedisSeqRangeMgr redisSeqRangeMgr = new RedisSeqRangeMgr();
		redisSeqRangeMgr.setStep(this.step);
		redisSeqRangeMgr.setStepStart(this.stepStart);
		redisSeqRangeMgr.setSeqRedisTemplate(seqRedisTemplate);

		redisSeqRangeMgr.init();
		//构建序列号生成器
		DefaultRangeSequence sequence = new DefaultRangeSequence();
		sequence.setName(this.bizName);
		sequence.setSeqRangeMgr(redisSeqRangeMgr);
		return sequence;
	}



	public RedisSeqBuilder step(int step) {
		this.step = step;
		return this;
	}

	public RedisSeqBuilder bizName(BizName bizName) {
		this.bizName = bizName;
		return this;
	}

	public RedisSeqBuilder stepStart(long stepStart) {
		this.stepStart = stepStart;
		return this;
	}
	public RedisSeqBuilder seqRedisTemplate(RedisTemplate seqRedisTemplate) {
		this.seqRedisTemplate = seqRedisTemplate;
		return this;
	}

}
