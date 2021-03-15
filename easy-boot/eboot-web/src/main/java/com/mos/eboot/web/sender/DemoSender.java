package com.mos.eboot.web.sender;

import com.mos.eboot.tools.util.IDGen;
import com.mos.eboot.web.config.RabbitConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 小尘哥
 */
@Component
public class DemoSender implements RabbitTemplate.ConfirmCallback{

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoSender.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public DemoSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
    }

    public void send(String msg) {
        CorrelationData correlationData = new CorrelationData(IDGen.genId());
        LOGGER.info("send: " + correlationData.getId());
        this.rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE, RabbitConstants.ROUTINGKEY, msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        System.out.println("confirm: " + correlationData.getId());

    }
}
