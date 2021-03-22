package org.springrain.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.SpringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 无序消费
 */
public class FrameMessageListenerConcurrently implements MessageListenerConcurrently {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RocketmqProperties rocketmqProperties;

    public FrameMessageListenerConcurrently(RocketmqProperties rocketmqProperties) {
        this.rocketmqProperties = rocketmqProperties;
    }


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        StringBuilder sb = new StringBuilder();
        msgs.forEach(one -> {
            try {
                sb.append(new String(one.getBody(), RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                logger.error(e.getMessage(), e);
            }
        });
        String topic = context.getMessageQueue().getTopic();
        MQMessageInfo messageInfo = new MQMessageInfo();
        messageInfo.setMessage(sb.toString());
        messageInfo.setTopic(topic);
        messageInfo.setBroker(context.getMessageQueue().getBrokerName());
        messageInfo.setConsumerGroupName(rocketmqProperties.getConsumerGroupName());
        logger.info("consumerGroupName:{},{},receive:{}", rocketmqProperties.getConsumerGroupName(), messageInfo.getMessage(), sb.toString());
        try {
            String beanTopic = topic.replaceAll("%RETRY%", "");
            boolean isRetry = topic.contains("%RETRY%");
            RocketMQMessageHandler rocketMQMessageHandler = (RocketMQMessageHandler) SpringUtils.getBean(RocketmqProperties.TopicHandlerPrefix + beanTopic);

            if (rocketMQMessageHandler == null) {
                logger.error("{},未实现RocketMQMessageListener接口", RocketmqProperties.TopicHandlerPrefix + topic);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            RocketMQConsumeStatus rocketMQConsumeStatus;
            if (isRetry) {
                rocketMQConsumeStatus = rocketMQMessageHandler.retryConsumeMessage(messageInfo);
            } else {
                rocketMQConsumeStatus = rocketMQMessageHandler.consumeMessage(messageInfo);
            }

            if (rocketMQConsumeStatus == RocketMQConsumeStatus.CONSUME_SUCCESS) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        } catch (Exception ex) {
            logger.error("获取bean:{}失败", RocketmqProperties.TopicHandlerPrefix + topic, ex);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;//.CONSUME_SUCCESS;

    }


}
