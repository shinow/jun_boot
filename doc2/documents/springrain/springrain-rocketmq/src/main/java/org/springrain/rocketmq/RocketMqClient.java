package org.springrain.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.JsonUtils;
import org.springrain.frame.util.SpringUtils;

import java.util.List;
import java.util.concurrent.*;


/**
 * RocketMqClient
 */

public class RocketMqClient {

    RocketmqProperties rocketmqProperties;
    // 声明线程池
    ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("client-transaction-msg-check-thread");
            return thread;
        }
    });
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String logFlag = "rocketMq";
    private DefaultMQProducer defaultProducer;
    private TransactionMQProducer defaultTransactionProducer;


    public RocketMqClient() {

    }


    public RocketMqClient(RocketmqProperties _rocketmqProperties) throws MQClientException {
        rocketmqProperties = _rocketmqProperties;
    }


    /**
     * 创建普通的生产者
     *
     * @throws MQClientException
     */
    public void createProducer() throws MQClientException {
        // 设置生产者属性
        defaultProducer = new DefaultMQProducer(rocketmqProperties.getProducerGroupName());
        defaultProducer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        defaultProducer.setInstanceName(rocketmqProperties.getProducerInstanceName());
        // VipChannel阿里内部使用版本才用,开源版本没有,可以直接设置为false
        defaultProducer.setVipChannelEnabled(false);
        defaultProducer.start();
    }


    /**
     * 创建带事务的生产者
     * <p>
     * http://rocketmq.apache.org/docs/transaction-example/
     *
     * @throws MQClientException
     */
    public void createTransactionProducer() throws MQClientException {

        // 设置生产者属性
        defaultTransactionProducer = new TransactionMQProducer(rocketmqProperties.getTransactionProducerGroupName());
        defaultTransactionProducer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        defaultTransactionProducer.setInstanceName(rocketmqProperties.getProducerTranInstanceName());
        // VipChannel阿里内部使用版本才用,开源版本没有,可以直接设置为false
        defaultTransactionProducer.setVipChannelEnabled(false);
        defaultTransactionProducer.setRetryTimesWhenSendAsyncFailed(10);

        // 设置线程池
        defaultTransactionProducer.setExecutorService(executorService);

        // 声明事务的监听器
        TransactionListener transactionListener = new
                TransactionListener() {
                    // 执行本地事务,进入监听器,消息服务器已经接受到消息
                    // 如果返回ROLLBACK_MESSAGE 消息,消费者端不会接受这条消息事务
                    // 如果返回COMMIT_MESSAGE 消费者端会接受这条事务
                    // 如果返回UNKNOW 或者异常没有返回值 ,服务端会调用checkLocalTransaction 补偿查询执行的状态
                    @Override
                    public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
                        // 需要 RocketMQMessageHandler 对应实现
                        String topic = message.getTopic();
                        RocketMQMessageHandler rocketMQMessageHandler = (RocketMQMessageHandler) SpringUtils.getBean(RocketmqProperties.TopicHandlerPrefix + topic);
                        if (rocketMQMessageHandler == null) {
                            logger.error("{},未实现RocketMQMessageListener接口", RocketmqProperties.TopicHandlerPrefix + topic);
                            return LocalTransactionState.UNKNOW;
                        }
                        return rocketMQMessageHandler.executeLocalTransaction(message, arg);
                    }

                    // 补偿查询本地事务是否完成,确定事务是提交还是回滚,类似充值回调.
                    @Override
                    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                        // 需要 RocketMQMessageHandler 对应实现
                        String topic = messageExt.getTopic();
                        RocketMQMessageHandler rocketMQMessageHandler = (RocketMQMessageHandler) SpringUtils.getBean(RocketmqProperties.TopicHandlerPrefix + topic);
                        if (rocketMQMessageHandler == null) {
                            logger.error("{},未实现RocketMQMessageListener接口", RocketmqProperties.TopicHandlerPrefix + topic);
                            return LocalTransactionState.UNKNOW;
                        }
                        return rocketMQMessageHandler.checkLocalTransaction(messageExt);
                    }
                };
        // 设置事务的监听器
        defaultTransactionProducer.setTransactionListener(transactionListener);
        // 启动生产者
        defaultTransactionProducer.start();

    }

    /**
     * 注册消费者监听器
     *
     * @throws MQClientException
     */
    public void registerConsumerListener() throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqProperties.getConsumerGroupName());
        consumer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        // VipChannel阿里内部使用版本才用,开源版本没有,可以直接设置为false
        consumer.setVipChannelEnabled(false);
        // 有 CLUSTERING 和 BROADCASTING 模式,CLUSTERING一条消息只有一个消费者,生产者-消费者模式.
        // BROADCASTING广播模式,订阅发布模式,一条消息会被所有的订阅者消费.
        // CLUSTERING 有重试机制,BROADCASTING没有重试机制,建议CLUSTERING模式
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 从消息队列头开始消费,默认是CONSUME_FROM_LAST_OFFSET
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //重复消费次数,用于失败后重试,默认16次
        // consumer.setMaxReconsumeTimes(5);

        List<String> subscribeList = rocketmqProperties.getSubscribe();
        // 订阅多个主题
        for (String sunscribe : subscribeList) {
            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
        }

        // MessageListenerConcurrently 无序消费
        // MessageListenerOrderly 有序消费
        if (rocketmqProperties.isEnableOrderConsumer()) {
            consumer.registerMessageListener(new FrameMessageListenerOrderly(rocketmqProperties));
        } else {
            consumer.registerMessageListener(new FrameMessageListenerConcurrently(rocketmqProperties));
        }
        // 启动消费者
        consumer.start();
    }


    /**
     * 发送普通消息
     *
     * @param topic
     * @param messageObject
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> boolean sendMessage(String topic, T messageObject) throws Exception {

        String s = JsonUtils.writeValueAsString(messageObject);
        Message msg = new Message(topic, "*", s.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = defaultProducer.send(msg);
        logger.info("{}:{} send message to {};result{}", logFlag, rocketmqProperties.getProducerGroupName(), topic, sendResult);
        return true;
    }

    /**
     * 发送事务消息
     *
     * @param topic
     * @param messageObject
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> boolean sendTranMessage(String topic, T messageObject) throws Exception {

        String s = JsonUtils.writeValueAsString(messageObject);
        Message msg = new Message(topic, "*", s.getBytes(RemotingHelper.DEFAULT_CHARSET));
        SendResult sendResult = defaultTransactionProducer.sendMessageInTransaction(msg, null);
        logger.info("{}:{} send message to {};result{}", logFlag, rocketmqProperties.getProducerGroupName(), topic, sendResult);
        return true;
    }

}
