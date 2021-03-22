package org.springrain.alipay.sdk.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorStationQueryModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherBatchqueryModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherGenerateModel;
import com.alipay.api.domain.AlipayCommerceCityfacilitatorVoucherRefundModel;
import com.alipay.api.request.AlipayCommerceCityfacilitatorStationQueryRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherBatchqueryRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherGenerateRequest;
import com.alipay.api.request.AlipayCommerceCityfacilitatorVoucherRefundRequest;
import com.alipay.api.response.AlipayCommerceCityfacilitatorStationQueryResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherBatchqueryResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherGenerateResponse;
import com.alipay.api.response.AlipayCommerceCityfacilitatorVoucherRefundResponse;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;

/**
 * 地铁购票API
 * https://docs.open.alipay.com/208/105243/
 */
public class AliPayCityfacilitatorApi {

    /**
     * 地铁购票发码
     * https://docs.open.alipay.com/api_32/alipay.commerce.cityfacilitator.voucher.generate
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherGenerateModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherGenerateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherGenerateResponse voucherGenerateToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherGenerateModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherGenerateRequest request = new AlipayCommerceCityfacilitatorVoucherGenerateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 地铁购票发码退款
     * <p>
     * https://docs.open.alipay.com/api_32/alipay.commerce.cityfacilitator.voucher.refund
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherRefundModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherRefundResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherRefundResponse metroRefundToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherRefundModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherRefundRequest request = new AlipayCommerceCityfacilitatorVoucherRefundRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 地铁车站数据查询
     * <p>
     * https://docs.open.alipay.com/api_32/alipay.commerce.cityfacilitator.station.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorStationQueryModel}
     * @return {@link AlipayCommerceCityfacilitatorStationQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorStationQueryResponse stationQueryToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorStationQueryModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorStationQueryRequest request = new AlipayCommerceCityfacilitatorStationQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 核销码批量查询
     * <p>
     * https://docs.open.alipay.com/api_32/alipay.commerce.cityfacilitator.voucher.batchquery
     *
     * @param aliPayConfig
     * @param model        {@link AlipayCommerceCityfacilitatorVoucherBatchqueryModel}
     * @return {@link AlipayCommerceCityfacilitatorVoucherBatchqueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static AlipayCommerceCityfacilitatorVoucherBatchqueryResponse voucherBatchqueryToResponse(IAliPayConfig aliPayConfig, AlipayCommerceCityfacilitatorVoucherBatchqueryModel model) throws AlipayApiException {
        AlipayCommerceCityfacilitatorVoucherBatchqueryRequest request = new AlipayCommerceCityfacilitatorVoucherBatchqueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

}
