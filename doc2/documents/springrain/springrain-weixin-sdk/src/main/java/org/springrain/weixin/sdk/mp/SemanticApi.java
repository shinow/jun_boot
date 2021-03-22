package org.springrain.weixin.sdk.mp;

import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.sdk.common.ApiResult;
import org.springrain.weixin.sdk.common.WxConsts;
import org.springrain.weixin.sdk.common.wxconfig.IWxMpConfig;

/**
 * 语义理解接口
 * <p>
 * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Intelligent_Interface/Natural_Language_Processing.html
 */
public class SemanticApi {

    private static String semanticUrl = WxConsts.mpapiurl + "/semantic/semproxy/search?access_token=";

    /**
     * 发送语义理解请求
     *
     * @param jsonStr POST数据格式：JSON
     * @return ApiResult
     */
    public static ApiResult search(IWxMpConfig wxmpconfig, String jsonStr) {
        String url = semanticUrl + wxmpconfig.getAccessToken();
        String jsonResult = HttpClientUtils.sendHttpPost(url, jsonStr);
        return new ApiResult(jsonResult);
    }

}
