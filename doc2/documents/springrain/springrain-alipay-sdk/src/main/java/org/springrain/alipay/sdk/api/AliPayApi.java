package org.springrain.alipay.sdk.api;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.apache.commons.lang3.StringUtils;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.frame.util.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 支付宝支付API
 * https://docs.open.alipay.com/api_1
 */
public class AliPayApi {

    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * app支付接口
     * https://docs.open.alipay.com/api_1/alipay.trade.pay
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeAppPayModel}
     * @param notifyUrl    异步通知 URL
     * @return {@link AlipayTradeAppPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeAppPayResponse appPayToResponse(IAliPayConfig aliPayConfig, AlipayTradeAppPayModel model, String notifyUrl) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).sdkExecute(request);
    }


    /**
     * 手机网站支付接口
     * https://docs.open.alipay.com/api_1/alipay.trade.wap.pay
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeWapPayModel}
     * @param returnUrl    异步通知URL
     * @param notifyUrl    同步通知URL
     * @return {String}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String wapPay(IAliPayConfig aliPayConfig, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) throws AlipayApiException {
        AlipayTradeWapPayRequest aliPayRequest = new AlipayTradeWapPayRequest();
        aliPayRequest.setReturnUrl(returnUrl);
        aliPayRequest.setNotifyUrl(notifyUrl);
        aliPayRequest.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).pageExecute(aliPayRequest).getBody();
    }


    /**
     * 统一收单交易支付接口<br>
     * 适用于:条形码支付、声波支付等 <br>
     * https://docs.open.alipay.com/api_1/alipay.trade.pay
     *
     * @param aliPayConfig
     * @param model        {AlipayTradePayModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {AlipayTradePayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePayResponse tradePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePayModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);

        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }

        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }


    /**
     * 统一收单线下交易预创建 <br>
     * 适用于：扫码支付等 <br>
     * https://docs.open.alipay.com/api_1/alipay.trade.precreate
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePrecreateModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradePrecreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradePrecreateResponse tradePrecreatePayToResponse(IAliPayConfig aliPayConfig, AlipayTradePrecreateModel model,
                                                                           String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);

        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }

    /**
     * 统一收单线下交易查询接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeQueryModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeQueryResponse tradeQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeQueryModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);

        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易撤销接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.cancel
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCancelModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCancelResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCancelResponse tradeCancelToResponse(IAliPayConfig aliPayConfig, AlipayTradeCancelModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizModel(model);
        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }


    /**
     * 统一收单交易关闭接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.close
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCloseModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCloseResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCloseResponse tradeCloseToResponse(IAliPayConfig aliPayConfig, AlipayTradeCloseModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizModel(model);
        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);

    }


    /**
     * 统一收单交易创建接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.create
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeCreateModel}
     * @param notifyUrl    异步通知URL
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeCreateResponse tradeCreateToResponse(IAliPayConfig aliPayConfig, AlipayTradeCreateModel model, String notifyUrl, String appAuthToken) throws AlipayApiException {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }


    /**
     * 统一收单交易退款接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeRefundModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeRefundResponse tradeRefundToResponse(IAliPayConfig aliPayConfig, AlipayTradeRefundModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }

    /**
     * 统一收单交易退款查询
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeFastpayRefundQueryModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeFastpayRefundQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayTradeFastpayRefundQueryResponse tradeRefundQueryToResponse(IAliPayConfig aliPayConfig, AlipayTradeFastpayRefundQueryModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model);

        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }


    /**
     * 统一收单交易结算接口
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.order.settle
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradeOrderSettleModel}
     * @param appAuthToken 应用授权token
     * @return {@link AlipayTradeOrderSettleResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    private static AlipayTradeOrderSettleResponse tradeOrderSettleToResponse(IAliPayConfig aliPayConfig, AlipayTradeOrderSettleModel model, String appAuthToken) throws AlipayApiException {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizModel(model);
        if (StringUtils.isBlank(appAuthToken)) {
            return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
        }
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request, null, appAuthToken);
    }


    /**
     * 统一收单下单并支付页面接口
     * 电脑网站支付(PC支付)
     * <p>
     * https://docs.open.alipay.com/api_1/alipay.trade.page.pay
     *
     * @param aliPayConfig
     * @param model        {@link AlipayTradePagePayModel}
     * @param notifyUrl    异步通知URL
     * @param returnUrl    同步通知URL
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String tradePage(IAliPayConfig aliPayConfig, AlipayTradePagePayModel model, String notifyUrl, String returnUrl) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String form = AliPayUtils.getAliPayCertClient(aliPayConfig).pageExecute(request).getBody();// 调用SDK生成表单
        return form;
    }


    /**
     * 应用授权 URL 拼装
     *
     * @param aliPayConfig
     * @param appId        应用编号
     * @param redirectUri  回调 URI
     * @return 应用授权 URL
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String getOauth2Url(IAliPayConfig aliPayConfig, String appId, String redirectUri) throws UnsupportedEncodingException {
        return new StringBuffer().append("https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=").append(appId).append("&redirect_uri=").append(URLEncoder.encode(redirectUri, "UTF-8")).toString();
    }

    /**
     * 使用 app_auth_code 换取 app_auth_token
     *
     * @param aliPayConfig
     * @param model        {@link AlipayOpenAuthTokenAppModel}
     * @return {@link AlipayOpenAuthTokenAppResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayOpenAuthTokenAppResponse openAuthTokenAppToResponse(IAliPayConfig aliPayConfig, AlipayOpenAuthTokenAppModel model) throws AlipayApiException {
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 查询某个应用授权AppAuthToken的授权信息
     * <p>
     * https://docs.open.alipay.com/api_9/alipay.open.auth.token.app.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayOpenAuthTokenAppQueryModel}
     * @return {@link AlipayOpenAuthTokenAppQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayOpenAuthTokenAppQueryResponse openAuthTokenAppQueryToResponse(IAliPayConfig aliPayConfig, AlipayOpenAuthTokenAppQueryModel model) throws AlipayApiException {
        AlipayOpenAuthTokenAppQueryRequest request = new AlipayOpenAuthTokenAppQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }


    /**
     * 批量付款到支付宝账户
     * https://docs.open.alipay.com/64/104804
     *
     * @param aliPayConfig
     * @param params
     * @param privateKey
     * @param signType
     * @return
     * @throws ParseException
     */
    public static String batchTrans(IAliPayConfig aliPayConfig, Map<String, String> params, String privateKey, String signType) throws ParseException {
        params.put("service", "batch_trans_notify");
        params.put("_input_charset", "UTF-8");
        params.put("pay_date", DateUtils.convertDate2String("YYYYMMDD", new Date()));
        Map<String, String> param = AliPayUtils.buildRequestPara(params, privateKey, signType);
        return GATEWAY_NEW.concat(AliPayUtils.createLinkString(param));
    }


    /**
     * 生活缴费查询账单
     * https://docs.open.alipay.com/api_18/
     * https://docs.open.alipay.com/api_18/alipay.ebpp.bill.get
     *
     * @param aliPayConfig
     * @param orderType       支付宝订单类型
     * @param merchantOrderNo 业务流水号
     * @return {@link AlipayEbppBillGetResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayEbppBillGetResponse ebppBillGet(IAliPayConfig aliPayConfig, String orderType, String merchantOrderNo) throws AlipayApiException {
        AlipayEbppBillGetRequest request = new AlipayEbppBillGetRequest();
        request.setOrderType(orderType);
        request.setMerchantOrderNo(merchantOrderNo);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

}