package org.springrain.alipay.sdk.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;

/**
 * 实名认证API
 * https://docs.open.alipay.com/api_46/
 */
public class AliPayZolozApi {

    /**
     * H5刷脸认证初始化
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.identification.user.web.initialize
     *
     * @param aliPayConfig
     * @param model        {@link ZolozIdentificationUserWebInitializeModel}
     * @return {@link ZolozIdentificationUserWebInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozIdentificationUserWebInitializeResponse zolozIdentificationUserWebInitialize(IAliPayConfig aliPayConfig, ZolozIdentificationUserWebInitializeModel model) throws AlipayApiException {
        ZolozIdentificationUserWebInitializeRequest request = new ZolozIdentificationUserWebInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * H5刷脸认证查询
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.identification.user.web.query
     *
     * @param aliPayConfig
     * @param model        {@link ZolozIdentificationUserWebQueryModel}
     * @return {@link ZolozIdentificationUserWebQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozIdentificationUserWebQueryResponse zolozIdentificationUserWebQuery(IAliPayConfig aliPayConfig, ZolozIdentificationUserWebQueryModel model) throws AlipayApiException {
        ZolozIdentificationUserWebQueryRequest request = new ZolozIdentificationUserWebQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 热脸入库
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.authentication.customer.facemanage.create
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFacemanageCreateModel}
     * @return {@link ZolozAuthenticationCustomerFacemanageCreateResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFacemanageCreateResponse zolozAuthenticationCustomerFaceManageCreate(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFacemanageCreateModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFacemanageCreateRequest request = new ZolozAuthenticationCustomerFacemanageCreateRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 热脸出库
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.authentication.customer.facemanage.delete
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFacemanageDeleteModel}
     * @return {@link ZolozAuthenticationCustomerFacemanageDeleteResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFacemanageDeleteResponse zolozAuthenticationCustomerFaceManageDelete(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFacemanageDeleteModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFacemanageDeleteRequest request = new ZolozAuthenticationCustomerFacemanageDeleteRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 人脸 ftoken 查询消费接口
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.authentication.customer.ftoken.query
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerFtokenQueryModel}
     * @return {@link ZolozAuthenticationCustomerFtokenQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerFtokenQueryResponse zolozAuthenticationCustomerFTokenQuery(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerFtokenQueryModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerFtokenQueryRequest request = new ZolozAuthenticationCustomerFtokenQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 刷脸支付初始化
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.authentication.smilepay.initialize
     *
     * @param model {@link ZolozAuthenticationSmilepayInitializeModel}
     * @return {@link ZolozAuthenticationSmilepayInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationSmilepayInitializeResponse zolozAuthenticationSmilePayInitialize(IAliPayConfig aliPayConfig, ZolozAuthenticationSmilepayInitializeModel model) throws AlipayApiException {
        ZolozAuthenticationSmilepayInitializeRequest request = new ZolozAuthenticationSmilepayInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 人脸初始化唤起zim
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.authentication.customer.smilepay.initialize
     *
     * @param aliPayConfig
     * @param model        {@link ZolozAuthenticationCustomerSmilepayInitializeModel}
     * @return {@link ZolozAuthenticationCustomerSmilepayInitializeResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozAuthenticationCustomerSmilepayInitializeResponse zolozAuthenticationCustomerSmilePayInitialize(IAliPayConfig aliPayConfig, ZolozAuthenticationCustomerSmilepayInitializeModel model) throws AlipayApiException {
        ZolozAuthenticationCustomerSmilepayInitializeRequest request = new ZolozAuthenticationCustomerSmilepayInitializeRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }

    /**
     * 人脸服务的结果查询(一体化)
     * <p>
     * https://docs.open.alipay.com/api_46/zoloz.identification.customer.certifyzhub.query
     *
     * @param aliPayConfig
     * @param model        {@link ZolozIdentificationCustomerCertifyzhubQueryModel}
     * @return {@link ZolozIdentificationCustomerCertifyzhubQueryResponse}
     * @throws AlipayApiException 支付宝 Api 异常
     */
    public static ZolozIdentificationCustomerCertifyzhubQueryResponse zolozIdentificationCustomerCertifyzhubQuery(IAliPayConfig aliPayConfig, ZolozIdentificationCustomerCertifyzhubQueryModel model) throws AlipayApiException {
        ZolozIdentificationCustomerCertifyzhubQueryRequest request = new ZolozIdentificationCustomerCertifyzhubQueryRequest();
        request.setBizModel(model);
        return AliPayUtils.getAliPayCertClient(aliPayConfig).certificateExecute(request);
    }


}
