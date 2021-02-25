package org.springrain.alipay.sdk.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.frame.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 资金API
 * https://docs.open.alipay.com/api_28/
 */
public class AliPayFundApi {


    /**
     * 单笔转账到支付宝账户
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransToaccountTransferModel}
     * @return 转账是否成功
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static boolean transfer(IAliPayConfig aliPayConfig, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
        AlipayFundTransToaccountTransferResponse response = transferToResponse(aliPayConfig, model);
        String result = response.getBody();
        if (response.isSuccess()) {
            return true;
        } else {
            // 调用查询接口查询数据
            Map mapResult = JsonUtils.readValue(result, HashMap.class);
            Map map = (Map) mapResult.get("alipay_fund_trans_toaccount_transfer_response");
            String outBizNo = (String) map.get("out_biz_no");
            AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
            model.setOutBizNo(outBizNo);
            return transferQuery(aliPayConfig, queryModel);
        }
    }

    /**
     * 单笔转账到支付宝账户
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransToaccountTransferModel}
     * @return {@link AlipayFundTransToaccountTransferResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    private static AlipayFundTransToaccountTransferResponse transferToResponse(IAliPayConfig aliPayConfig, AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 转账查询接口
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.order.query/
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransOrderQueryModel}
     * @return 是否存在此
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static boolean transferQuery(IAliPayConfig aliPayConfig, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
        AlipayFundTransOrderQueryResponse response = transferQueryToResponse(aliPayConfig, model);
        return response.isSuccess();
    }

    /**
     * 转账查询接口
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.order.query/
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundTransOrderQueryModel}
     * @return {@link AlipayFundTransOrderQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundTransOrderQueryResponse transferQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundTransOrderQueryModel model) throws AlipayApiException {
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }


    /**
     * 资金授权冻结接口
     * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.freeze
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderFreezeModel}
     * @return {@link AlipayFundAuthOrderFreezeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderFreezeResponse authOrderFreezeToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderFreezeModel model) throws AlipayApiException {
        AlipayFundAuthOrderFreezeRequest request = new AlipayFundAuthOrderFreezeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 资金授权解冻接口
     * <p>
     * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.unfreeze
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderUnfreezeModel}
     * @return {@link AlipayFundAuthOrderUnfreezeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderUnfreezeResponse authOrderUnfreezeToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderUnfreezeModel model) throws AlipayApiException {
        AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 资金授权发码接口
     * https://docs.open.alipay.com/api_28/alipay.fund.auth.order.voucher.create
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOrderVoucherCreateModel}
     * @return {@link AlipayFundAuthOrderVoucherCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOrderVoucherCreateResponse authOrderVoucherCreateToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOrderVoucherCreateModel model) throws AlipayApiException {
        AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 资金授权撤销接口
     * https://docs.open.alipay.com/api_28/alipay.fund.auth.operation.cancel
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOperationCancelModel}
     * @return {@link AlipayFundAuthOperationCancelResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOperationCancelResponse authOperationCancelToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOperationCancelModel model) throws AlipayApiException {
        AlipayFundAuthOperationCancelRequest request = new AlipayFundAuthOperationCancelRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 资金授权操作查询接口
     * https://docs.open.alipay.com/api_28/alipay.fund.auth.operation.detail.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundAuthOperationDetailQueryModel}
     * @return {@link AlipayFundAuthOperationDetailQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundAuthOperationDetailQueryResponse authOperationDetailQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundAuthOperationDetailQueryModel model) throws AlipayApiException {
        AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 现金红包无线支付接口
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.app.pay
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderAppPayModel}
     * @return {@link AlipayFundCouponOrderAppPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderAppPayResponse fundCouponOrderAppPayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderAppPayModel model) throws AlipayApiException {
        AlipayFundCouponOrderAppPayRequest request = new AlipayFundCouponOrderAppPayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 红包页面支付接口
     * https://docs.open.alipay.com/200/attentionapis
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderPagePayModel}
     * @return {@link AlipayFundCouponOrderPagePayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderPagePayResponse fundCouponOrderPagePayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderPagePayModel model) throws AlipayApiException {
        AlipayFundCouponOrderPagePayRequest request = new AlipayFundCouponOrderPagePayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 红包协议支付接口
     * https://docs.open.alipay.com/200/attentionapis
     * https://docs.open.alipay.com/api_5/alipay.fund.coupon.order.agreement.pay
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderAgreementPayModel}
     * @return {@link AlipayFundCouponOrderAgreementPayResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderAgreementPayResponse fundCouponOrderAgreementPayToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderAgreementPayModel model) throws AlipayApiException {
        AlipayFundCouponOrderAgreementPayRequest request = new AlipayFundCouponOrderAgreementPayRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 红包打款接口
     * https://docs.open.alipay.com/200/attentionapis
     * https://docs.open.alipay.com/api_5/alipay.fund.coupon.order.disburse
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderDisburseModel}
     * @return {@link AlipayFundCouponOrderDisburseResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderDisburseResponse fundCouponOrderDisburseToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderDisburseModel model) throws AlipayApiException {
        AlipayFundCouponOrderDisburseRequest request = new AlipayFundCouponOrderDisburseRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 红包退回接口
     * https://docs.open.alipay.com/200/attentionapis
     * https://docs.open.alipay.com/api_5/alipay.fund.coupon.order.refund
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOrderRefundModel}
     * @return {@link AlipayFundCouponOrderRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOrderRefundResponse fundCouponOrderRefundToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOrderRefundModel model) throws AlipayApiException {
        AlipayFundCouponOrderRefundRequest request = new AlipayFundCouponOrderRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 红包操作查询
     *
     * @param aliPayConfig
     * @param model        {@link AlipayFundCouponOperationQueryModel}
     * @return {@link AlipayFundCouponOperationQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayFundCouponOperationQueryResponse fundCouponOperationQueryToResponse(IAliPayConfig aliPayConfig, AlipayFundCouponOperationQueryModel model) throws AlipayApiException {
        AlipayFundCouponOperationQueryRequest request = new AlipayFundCouponOperationQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

}
