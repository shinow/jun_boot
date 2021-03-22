package org.springrain.rocketmq;

/**
 * 消息状态的枚举
 */
public enum RocketMQConsumeStatus {
    // MessageListenerConcurrently 无序消费 的状态
    CONSUME_SUCCESS,
    RECONSUME_LATER,
    // MessageListenerOrderly 有序消费 的状态
    //SUCCESS,
    //SUSPEND_CURRENT_QUEUE_A_MOMENT
    ;

    RocketMQConsumeStatus() {
    }
}
