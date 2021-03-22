package org.springrain.alipay.sdk.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import org.apache.commons.lang3.StringUtils;
import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.SecUtils;

import java.util.*;

/**
 * 支付宝SDK工具类
 */
public class AliPayUtils {

    /**
     * 生成签名结果
     *
     * @param params   要签名的数组
     * @param key      签名密钥
     * @param signType 签名类型
     * @return 签名结果字符串
     */
    public static String buildRequestMySign(Map<String, String> params, String key, String signType) {
        // 把数组所有元素，按照"参数=参数值"的模式用"&"字符拼接成字符串
        String preStr = createLinkString(params);
        if ("md5".equalsIgnoreCase(signType)) {
            return SecUtils.encoderByMd5With32Bit(preStr.concat(key));
        }
        return null;
    }

    /**
     * 生成要请求给支付宝的参数数组
     *
     * @param params   请求前的参数数组
     * @param key      商户的私钥
     * @param signType 签名类型
     * @return 要请求的参数数组
     */
    public static Map<String, String> buildRequestPara(Map<String, String> params, String key, String signType) {
        // 除去数组中的空值和签名参数
        Map<String, String> tempMap = paraFilter(params);
        // 生成签名结果
        String mySign = buildRequestMySign(params, key, signType);

        // 签名结果与签名方式加入请求提交参数组中
        tempMap.put("sign", mySign);
        tempMap.put("sign_type", signType);

        return tempMap;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<>(sArray.size());
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key)
                    || "sign_type".equalsIgnoreCase(key)) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把数组所有元素排序
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                content.append(key + "=" + value);
            } else {
                content.append(key + "=" + value + "&");
            }
        }
        return content.toString();
    }


    /**
     * 将异步通知的参数转化为Map
     *
     * @param requestParams
     * @return
     */
    public static Map<String, String> toMap(Map<String, String[]> requestParams) {
        Map<String, String> params = new HashMap<>();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 获取返回请求的 AlipayClient,没有证书安全,资金类的接口是强制要求使用证书的.
     *
     * @param aliPayConfig
     * @return
     */
    /*
    public static AlipayClient getAliPayClient(IAliPayConfig aliPayConfig) {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getServiceUrl(), aliPayConfig.getAppId(), aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(),
                aliPayConfig.getCharset(), aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
        return alipayClient;
    }
    */

    /**
     * 返回带证书的 AlipayClient
     *
     * @param aliPayConfig
     * @return
     * @throws AlipayApiException
     */
    public static AlipayClient getAliPayCertClient(IAliPayConfig aliPayConfig) throws AlipayApiException {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(aliPayConfig.getServiceUrl());
        certAlipayRequest.setAppId(aliPayConfig.getAppId());
        certAlipayRequest.setPrivateKey(aliPayConfig.getPrivateKey());
        certAlipayRequest.setFormat(aliPayConfig.getFormat());
        certAlipayRequest.setCharset(aliPayConfig.getCharset());
        certAlipayRequest.setSignType(aliPayConfig.getSignType());

        String certPath = aliPayConfig.getCertPath();
        if (!(certPath.startsWith("file:/") || certPath.startsWith("/"))) {
            certPath = GlobalStatic.rootDir + "/" + certPath;
        }
        certAlipayRequest.setCertPath(certPath);

        String alipayPublicCertPath = aliPayConfig.getAlipayPublicCertPath();
        if (!(alipayPublicCertPath.startsWith("file:/") || alipayPublicCertPath.startsWith("/"))) {
            alipayPublicCertPath = GlobalStatic.rootDir + "/" + alipayPublicCertPath;
        }
        certAlipayRequest.setAlipayPublicCertPath(alipayPublicCertPath);

        String rootCertPath = aliPayConfig.getRootCertPath();
        if (!(rootCertPath.startsWith("file:/") || rootCertPath.startsWith("/"))) {
            rootCertPath = GlobalStatic.rootDir + "/" + rootCertPath;
        }
        certAlipayRequest.setRootCertPath(rootCertPath);


        //设置加密类型AES
        certAlipayRequest.setEncryptType(aliPayConfig.getEncryptType());

        //设置加密key
        if (StringUtils.isNotBlank(aliPayConfig.getAesKey())) {
            certAlipayRequest.setEncryptor(aliPayConfig.getAesKey());
        }

        AlipayClient alipayClient = new DefaultAlipayClient(certAlipayRequest);
        return alipayClient;
    }


}