package org.springrain.weixin.sdk.miniapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.frame.util.HttpClientUtils;
import org.springrain.frame.util.JsonUtils;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.wxconfig.IWxMiniappConfig;

/**
 * 发送模板消息
 * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/template-message/templateMessage.send.html
 */
public class MiniappTemplateMessageApi {
    private static final Logger logger = LoggerFactory.getLogger(MiniappTemplateMessageApi.class);
    private static String sendUrl = WxConsts.mpapiurl + "/cgi-bin/message/wxopen/template/send?access_token=";

    private MiniappTemplateMessageApi() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static ApiResult send(IWxMiniappConfig config, MiniappTemplateMessage miniappTemplateMessage) throws Exception {
        String apiurl = sendUrl + config.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(apiurl, JsonUtils.writeValueAsString(miniappTemplateMessage.getTemplateMessageMap()));
        return new ApiResult(jsonResult);
    }


}
