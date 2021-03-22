package org.springrain.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.SpringUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 有序消费
 */
public class FrameMessageListenerOrderly implements MessageListenerOrderly {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RocketmqProperties rocketmqProperties;

    public FrameMessageListenerOrderly(RocketmqProperties rocketmqProperties) {
        this.rocketmqProperties = rocketmqProperties;
    }


    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
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
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }

            RocketMQConsumeStatus rocketMQConsumeStatus;
            if (isRetry) {
                rocketMQConsumeStatus = rocketMQMessageHandler.retryConsumeMessage(messageInfo);
            } else {
                rocketMQConsumeStatus = rocketMQMessageHandler.consumeMessage(messageInfo);
            }

            if (rocketMQConsumeStatus == RocketMQConsumeStatus.CONSUME_SUCCESS) {
                return ConsumeOrderlyStatus.SUCCESS;
            }

        } catch (Exception ex) {
            logger.error("获取bean:{}失败", RocketmqProperties.TopicHandlerPrefix + topic, ex);
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }
        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;//.CONSUME_SUCCESS;

    }


}
