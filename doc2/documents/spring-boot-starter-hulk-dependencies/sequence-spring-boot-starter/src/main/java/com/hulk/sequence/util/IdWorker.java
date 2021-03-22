package com.hulk.sequence.util;

import com.hulk.sequence.sequence.impl.SnowflakeSequence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 高效GUID产生算法(sequence),基于Snowflake实现64位自增ID算法。
 * <p>优化开源项目 https://gitee.com/yu120/sequence</p>
 *
 * @author hulk
 * @since 2018-08-01
 */
public class IdWorker {

    private static final String DASH = "-";

    private static final String EMPTY = "";
    /**
     * 主机和进程的机器码
     */
    private static SnowflakeSequence WORKER = new SnowflakeSequence();

    /**
     * 毫秒格式化时间
     */
    public static final DateTimeFormatter MILLISECOND = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static long getId() {
        return WORKER.nextId();
    }

    public static String getIdStr() {
        return String.valueOf(WORKER.nextId());
    }

    /**
     * 格式化的毫秒时间
     */
    public static String getMillisecond() {
        return LocalDateTime.now().format(MILLISECOND);
    }

    /**
     * 时间 ID = Time + ID
     * <p>例如：可用于商品订单 ID</p>
     */
    public static String getTimeId() {
        return getMillisecond() + getId();
    }

    /**
     * 有参构造器
     *
     * @param dataCenterId 序列号
     */
    public static void initSequence( long dataCenterId) {
        WORKER = new SnowflakeSequence(dataCenterId);
    }

    /**
     * 有参构造器
     *
     * @param dataCenterId   数据中心ID,数据范围为0~255
     * @param clock          true表示解决高并发下获取时间戳的性能问题
     * @param randomSequence true表示使用毫秒内的随机序列(超过范围则取余)
     */
    public static void initSequence( long dataCenterId, boolean clock, boolean randomSequence) {
        WORKER = new SnowflakeSequence( dataCenterId,clock,randomSequence);
    }

    /**
     * 使用ThreadLocalRandom获取UUID获取更优的效果 去掉"-"
     */
    public static String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString().replace(DASH, EMPTY);
    }



    public static String getBinaryId(){
        return Long.toBinaryString(IdWorker.getId());
    }

    public static String getOctId(){
        return Long.toOctalString(getId());
    }

    public static String getHexId(){
        return Long.toHexString(getId());
    }

    public static String getHEXId(){
        return Long.toHexString(getId()).toUpperCase();
    }

    public static String getDtmId(){
        return Long.toString(getId(), 32);
    }

    public static String getDTMId(){
        return Long.toString(getId(), 32).toUpperCase();
    }

    public static String getRadixId(int radix){
        return Long.toString(getId(), radix);
    }

    public static String getRADIXId(int radix){
        return Long.toString(getId(), radix).toUpperCase();
    }
}