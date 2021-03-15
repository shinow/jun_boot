package com.ifast.wxmp.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifast.wxmp.service.WeixinService;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
 * <pre>
 * </pre>
 * 
 * <small> 2018年6月13日 | Aron</small>
 */
public abstract class AbstractBuilder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WeixinService service);
}
