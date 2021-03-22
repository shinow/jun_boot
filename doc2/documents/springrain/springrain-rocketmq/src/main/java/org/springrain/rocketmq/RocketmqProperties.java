package org.springrain.rocketmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(RocketmqProperties.PREFIX)
public class RocketmqProperties {
    public static final String PREFIX = "springrain.rocketmq";
    public static final String TopicHandlerPrefix = "rmq_topic_consumer_";
    private String namesrvAddr;
    private String producerGroupName;
    private String transactionProducerGroupName;
    private String consumerGroupName;
    private String producerInstanceName;
    private String consumerInstanceName;
    private String producerTranInstanceName;
    private int consumerBatchMaxSize;
    private boolean consumerBroadcasting;
    private boolean enableHisConsumer;
    private boolean enableOrderConsumer;
    private List subscribe = new ArrayList<>();

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public String getTransactionProducerGroupName() {
        return transactionProducerGroupName;
    }

    public void setTransactionProducerGroupName(String transactionProducerGroupName) {
        this.transactionProducerGroupName = transactionProducerGroupName;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getProducerInstanceName() {
        return producerInstanceName;
    }

    public void setProducerInstanceName(String producerInstanceName) {
        this.producerInstanceName = producerInstanceName;
    }

    public String getConsumerInstanceName() {
        return consumerInstanceName;
    }

    public void setConsumerInstanceName(String consumerInstanceName) {
        this.consumerInstanceName = consumerInstanceName;
    }

    public String getProducerTranInstanceName() {
        return producerTranInstanceName;
    }

    public void setProducerTranInstanceName(String producerTranInstanceName) {
        this.producerTranInstanceName = producerTranInstanceName;
    }

    public int getConsumerBatchMaxSize() {
        return consumerBatchMaxSize;
    }

    public void setConsumerBatchMaxSize(int consumerBatchMaxSize) {
        this.consumerBatchMaxSize = consumerBatchMaxSize;
    }

    public boolean isConsumerBroadcasting() {
        return consumerBroadcasting;
    }

    public void setConsumerBroadcasting(boolean consumerBroadcasting) {
        this.consumerBroadcasting = consumerBroadcasting;
    }

    public boolean isEnableHisConsumer() {
        return enableHisConsumer;
    }

    public void setEnableHisConsumer(boolean enableHisConsumer) {
        this.enableHisConsumer = enableHisConsumer;
    }

    public boolean isEnableOrderConsumer() {
        return enableOrderConsumer;
    }

    public void setEnableOrderConsumer(boolean enableOrderConsumer) {
        this.enableOrderConsumer = enableOrderConsumer;
    }

    public List getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List subscribe) {
        this.subscribe = subscribe;
    }
}