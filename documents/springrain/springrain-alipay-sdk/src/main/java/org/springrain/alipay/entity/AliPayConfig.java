package org.springrain.alipay.entity;

import org.springrain.alipay.sdk.common.aliconfig.IAliPayConfig;
import org.springrain.frame.entity.BaseEntity;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支付宝的配置,
 */
@Table(name = "ali_payconfig")
public class AliPayConfig extends BaseEntity implements IAliPayConfig {
    private static final long serialVersionUID = 1L;


    private String id;
    private String privateKey;
    private String aliPayPublicKey;
    private String appId;
    private String serviceUrl = "https://openapi.alipay.com/gateway.do";
    private String charset = "UTF-8";
    private String signType = "RSA2";
    private String format = "json";
    //应用公钥证书路径
    private String certPath;
    //支付宝公钥证书文件路径
    private String alipayPublicCertPath;
    //支付宝CA根证书文件路径
    private String rootCertPath;

    private String encryptType = "AES";

    private String aesKey;


    public AliPayConfig() {

    }


    @Override
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    @Override
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String getCertPath() {
        return this.certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    @Override
    public String getAlipayPublicCertPath() {
        return this.alipayPublicCertPath;
    }

    public void setAlipayPublicCertPath(String alipayPublicCertPath) {
        this.alipayPublicCertPath = alipayPublicCertPath;
    }

    @Override
    public String getRootCertPath() {
        return this.rootCertPath;
    }

    public void setRootCertPath(String rootCertPath) {
        this.rootCertPath = rootCertPath;
    }

    @Override
    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    @Override
    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

}
