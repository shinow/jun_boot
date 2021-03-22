package com.mos.eboot.web.controller;


import com.mos.eboot.tools.controller.BaseController;
import com.mos.eboot.tools.result.ResultModel;
import com.mos.eboot.web.config.RabbitConstants;
import com.mos.eboot.web.sender.DemoSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 小尘哥
 */
@RestController
@RequestMapping("demo")
public class DemoController extends BaseController  {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private DemoSender demoSender;

    @RequestMapping("index")
    public String index(HttpSession session){
        session.setAttribute("sessionId",session.getId());
        session.setAttribute("sessionUserId","123");
        return "test";
    }

    @RequestMapping("session")
    public String session(HttpSession session){
        String sessionId = session.getAttribute("sessionId").toString();
        String sessionUserId = session.getAttribute("sessionUserId").toString();
        return sessionId + "^" +  sessionUserId;
    }

    @RequestMapping("amqp")
    public ResultModel amqp(){
        rabbitTemplate.convertAndSend(RabbitConstants.QUEUE,"1message from web");
        rabbitTemplate.convertAndSend("exchange","topic.messages","2message from web for exchage");
        rabbitTemplate.convertAndSend(RabbitConstants.EXCHANGE,RabbitConstants.ROUTINGKEY,"3message from web for fanoutExchange");

        demoSender.send("message from web for fanoutExchange1234234");
        return ResultModel.defaultSuccess(null);
    }
}
