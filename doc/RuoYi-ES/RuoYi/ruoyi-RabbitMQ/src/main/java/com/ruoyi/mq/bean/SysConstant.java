package com.ruoyi.mq.bean;

/**
 * @author japhet_jiu
 * @version 1.0
 */
public class SysConstant {

    /**
     * 测试队列
     */
    public static final String QUEUE_TEST_HELLO = "QUEUE_TEST_HELLO";

    /**死信交换机**/
    public static final String SYS_ORDER_DELAY_EXCHANGE = "SYS_ORDER_DELAY_EXCHANGE";
    /**接收死信队列消息**/
    public static final String SYS_ORDER_RECEIVE_EXCHANGE = "SYS_ORDER_RECEIVE_EXCHANGE";
    /**死信接收队列**/
    public static final String SYS_ORDER_RECEIVE_QUEUE = "SYS_ORDER_RECEIVE_QUEUE";
    /**延时队列**/
    public static final String SYS_ORDER_DELAY_QUEUE = "SYS_ORDER_DELAY_QUEUE";
    /**路由key**/
    public static final String SYS_ORDER_RECEIVE_KEY = "SYS_ORDER_RECEIVE_KEY";
    /**死信队列路由key**/
    public static final String SYS_ORDER_DELAY_KEY = "SYS_ORDER_DELAY_KEY";
}
