package org.springrain.alipay.sdk.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;

/**
 * 财务API
 * https://docs.open.alipay.com/api_15/
 */
public class AliPayBillApi {

    /**
     * 查询对账单下载地址
     * <p>
     * https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
     * @return 对账单下载地址
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static String billDownloadurlQuery(IAliPayConfig aliPayConfig, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryResponse response = billDownloadUrlQueryToResponse(aliPayConfig, model);
        return response.getBillDownloadUrl();
    }

    /**
     * 查询对账单下载地址
     * https://docs.open.alipay.com/api_15/alipay.data.dataservice.bill.downloadurl.query
     *
     * @param aliPayConfig
     * @param model        {@link AlipayDataDataserviceBillDownloadurlQueryModel}
     * @return {@link AlipayDataDataserviceBillDownloadurlQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    private static AlipayDataDataserviceBillDownloadurlQueryResponse billDownloadUrlQueryToResponse(IAliPayConfig aliPayConfig, AlipayDataDataserviceBillDownloadurlQueryModel model) throws AlipayApiException {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }


}
