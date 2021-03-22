package com.hulk.sequence.range.impl.redis;


import com.hulk.sequence.exception.SeqException;
import com.hulk.sequence.range.SeqRange;
import com.hulk.sequence.range.SeqRangeMgr;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis区间管理器
 *
 * @author hulk on 2018/5/8.
 */
@Data
public class RedisSeqRangeMgr implements SeqRangeMgr {

	/**
	 * 前缀防止key重复
	 */
	private final static String KEY_PREFIX = "x_sequence_";



	private RedisTemplate seqRedisTemplate;

	/**
	 * 区间步长
	 */
	private int step = 1;

	/**
	 * 区间起始位置，真实从stepStart+1开始
	 */
	private long stepStart = 0;

	/**
	 * 标记业务key是否存在，如果false，在取nextRange时，会取check一把
	 * 这个boolean只为提高性能，不用每次都取redis check
	 */
	private volatile boolean keyAlreadyExist;

	@Override
	public SeqRange nextRange(String name) throws SeqException {
		if (!keyAlreadyExist) {
			//Boolean isExists = jedis.exists(getRealKey(name));
			Boolean isExists =	seqRedisTemplate.hasKey(getRealKey(name));

			if (!isExists) {
				//第一次不存在，进行初始化,setnx不存在就set，存在就忽略
				//jedis.setnx(getRealKey(name), String.valueOf(stepStart));
				seqRedisTemplate.opsForValue().setIfAbsent(getRealKey(name), String.valueOf(stepStart));


			}
			keyAlreadyExist = true;
		}
		//Long max = jedis.incrBy(getRealKey(name), step);
		Long max =seqRedisTemplate.opsForValue().increment(getRealKey(name), step);
		Long min = max - step + 1;
		return new SeqRange(min, max);
	}

	@Override
	public void init() {

	}

	private String getRealKey(String name) {
		return KEY_PREFIX + name;
	}

}
