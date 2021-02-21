package com.ruoyi.mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author japhet_jiu
 * @version 1.0
 */
@Service
public class MsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送普通消息到消费队列
     * @param queue
     * @param msg
     */
    public void sendMsg(String queue,String msg) {
        rabbitTemplate.convertAndSend(queue,msg);
    }

    /**
     * 发送延时消息到mq
     * @param exchange 死信交换机
     * @param routeKey 路由key
     * @param data 发送数据
     * @param delayTime 过期时间，单位毫秒
     */
    public void sendDelayMsgToMQ(String exchange, String routeKey, String data,int delayTime) {
        rabbitTemplate.convertAndSend(exchange, routeKey, data, message -> {
            message.getMessageProperties().setExpiration(delayTime + "");
            return message;
        });
    }
}
