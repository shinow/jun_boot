package org.springrain.rocketmq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 消息处理的接口,用于逻辑分拆实现
 */
public interface RocketMQMessageHandler {
    /**
     * 处理普通消息
     *
     * @param messageInfo
     * @return
     */
    RocketMQConsumeStatus consumeMessage(MQMessageInfo messageInfo);


    /**
     * 处理重试的接口实现
     *
     * @param messageInfo
     * @return
     */
    RocketMQConsumeStatus retryConsumeMessage(MQMessageInfo messageInfo);


    /**
     * 执行本地事务,进入监听器,消息服务器已经接受到消息
     * 如果返回ROLLBACK_MESSAGE 消息,消费者端不会接受这条消息事务
     * 如果返回COMMIT_MESSAGE 消费者端会接受这条事务
     * 如果返回UNKNOW 或者异常没有返回值 ,服务端会调用checkLocalTransaction 补偿查询执行的状态
     *
     * @param message
     * @param arg
     * @return
     */
    default LocalTransactionState executeLocalTransaction(Message message, Object arg) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 补偿查询本地事务是否完成,确定事务是提交还是回滚,类似充值回调.
     *
     * @param messageExt
     * @return
     */
    default LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }


}
