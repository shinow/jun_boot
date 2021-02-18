package com.yiran.pay.sdk.service;

/**
 * 加解密
 */
public interface EncryptAndDecryptService {

    /**
     * 加密
     * @param key
     * @param data
     * @return
     */
    Object encrypt(String key, String data);


    /**
     * 解密
     * @param key
     * @param data
     * @return
     */
    Object decrypt(String key, String data);
}
