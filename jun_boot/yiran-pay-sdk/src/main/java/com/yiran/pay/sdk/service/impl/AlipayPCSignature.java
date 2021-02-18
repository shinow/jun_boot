package com.yiran.pay.sdk.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.yiran.pay.sdk.config.AliDirectPayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.constants.AlipayConstants;
import com.yiran.pay.sdk.utils.HttpRequestUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

/**
 * 支付宝即时到账方式签名
 *
 */
class AlipayPCSignature extends AbstractComponent {

    private AliDirectPayConfig aliDirectPayConfig;

    public AlipayPCSignature(AliDirectPayConfig aliDirectPayConfig) {
        Objects.requireNonNull(aliDirectPayConfig, "aliDirectPayConfig is null.");
        this.aliDirectPayConfig = aliDirectPayConfig;
    }

    public String sign(SortedMap<String, String> sortedParamMap) {
        List<String> paramList = new ArrayList<>();
        sortedParamMap.forEach((k, v) -> {
            if (StringUtils.isBlank(k) || k.equals("sign") || k.equals("sign_type") || StringUtils.isBlank(v)) {
                return;
            }

            paramList.add(k + "=" + v);
        });
        String param = String.join("&", paramList);
        switch (this.aliDirectPayConfig.getSignType()) {
            case MD5:
                return signParamWithMD5(param);
            case RSA:
                return signParamWithRSA(param);
            default:
                throw new IllegalStateException("unsupported sign type.");
        }
    }

    public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
        Objects.requireNonNull(toBeVerifiedParamMap, "to be verified param map is null.");
        if (toBeVerifiedParamMap.isEmpty()) {
            throw new IllegalArgumentException("to be verified param map is empty.");
        }

        Objects.requireNonNull(signType, "sign type is null.");
        switch (signType) {
            case RSA2:
                throw new IllegalArgumentException("unsupported sign type: RSA2.");
        }

        if (StringUtils.isBlank(sign)) {
            throw new IllegalArgumentException("sign is blank.");
        }

        /* 1. 验签 */
        List<String> paramList = new ArrayList<>();
        toBeVerifiedParamMap.forEach((k, v) -> {
            if (StringUtils.isBlank(k) || k.equals("sign_type") || k.equals("sign") || StringUtils.isEmpty(v)) {
                return;
            }

            paramList.add(k + "=" + v);
        });
        Collections.sort(paramList);
        String toBeVerifiedStr = String.join("&", paramList);

        boolean r = false;
        switch (signType) {
            case MD5:
                r = verifyParamWithMD5(toBeVerifiedStr, sign);
                break;
            case RSA:
                r = verifyParamWithRSA(toBeVerifiedStr, sign);
                break;
        }
        if (!r) {
            this.logger.warn("fail to verify sign with sign type {}.", signType.name());
            return false;
        }

        /* 2. 校验notify_id, 同步返回是没有notify_id参数的 */
        String notifyId = toBeVerifiedParamMap.get("notify_id");
        if (!StringUtils.isEmpty(notifyId)) {
            r = verifyNotifyId(notifyId);
            if (!r) {
                this.logger.warn("fail to verify notify id.");
                return false;
            }
        }

        return true;
    }

    /**
     * 验证notifyId, 注意notifyId的时效性大约为1分钟
     *
     * @param notifyId
     * @return
     */
    private boolean verifyNotifyId(String notifyId) {
        String veryfyUrl = AlipayConstants.ALIPAY_VERIFY_URL + "partner=" + this.aliDirectPayConfig.getPartnerId() + "&notify_id=" + notifyId;
        String result = HttpRequestUtil.get(veryfyUrl);

        if (result.equals("true")) {
            return true;
        }

        return false;
    }

    private String signParamWithMD5(String param) {
        return DigestUtils.md5Hex(param + this.aliDirectPayConfig.getPartnerMD5Key());
    }

    private String signParamWithRSA(String param) {
        try {
            java.security.Signature sig = java.security.Signature.getInstance("SHA1WithRSA");
            sig.initSign(this.aliDirectPayConfig.getPartnerRSAPrivateKeyObject());
            sig.update(param.getBytes(this.aliDirectPayConfig.getInputCharset()));
            return Base64.getEncoder().encodeToString(sig.sign());
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            throw new IllegalStateException("sign error.", e);
        }
    }

    private boolean verifyParamWithMD5(String param, String sign) {
        String expectedSign = DigestUtils.md5Hex(param + this.aliDirectPayConfig.getPartnerMD5Key());
        return sign.equals(expectedSign);
    }

    private boolean verifyParamWithRSA(String param, String sign) {
        try {
            java.security.Signature sig = java.security.Signature.getInstance("SHA1WithRSA");
            sig.initVerify(this.aliDirectPayConfig.getAlipayRSAPublicKeyObject());
            sig.update(param.getBytes("utf-8"));
            return sig.verify(Base64.getDecoder().decode(sign));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException e) {
            throw new IllegalStateException("AliPay verify error.");
        }
    }

}
