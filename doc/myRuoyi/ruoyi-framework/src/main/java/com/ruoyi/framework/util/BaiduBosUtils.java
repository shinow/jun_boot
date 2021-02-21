package com.ruoyi.framework.util;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.ruoyi.common.config.Global;

import java.io.File;
import java.io.InputStream;

/**
 *百度对象存储
 *@author PanMeiCheng
 *@date 2018-12-16
 *@company DM
 *@version 1.0
 */
public class BaiduBosUtils {
    private static final String endpoint = "http://gz.bcebos.com";
    private static final String ACCESS_KEY_ID = "76cc3ad1abef4a75937a4f56fdc77719";                   // 用户的Access Key ID
    private static final String SECRET_ACCESS_KEY = "888ec7c278e24341a294e397cb0814b2";           // 用户的Secret Access Key
    private static final String defultBucketName = "ruoyi";           // 用户的Secret Access Key
    public static final String baseUrl = Global.getNetFileBaseUrl();           // 公读文件基础路径
    private static BosClient bosClient = Singleton.INSTANCE.getInstanceBosClient();           // BosClient

    private BaiduBosUtils(){}


    private enum Singleton{
        INSTANCE;
        private BosClient singleton;
        //JVM会保证此方法绝对只调用一次
        Singleton(){
            // 初始化一个BosClient
            BosClientConfiguration config = new BosClientConfiguration();
            config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
            config.setEndpoint(endpoint);
            singleton = new BosClient(config);
        }
        public BosClient getInstanceBosClient(){
            return singleton;
        }
    }
    public static void deleteWebFile(String fileName) {
        bosClient.deleteObject(defultBucketName,fileName);
    }
    public static InputStream downWebFile(String fileName) {
        return bosClient.getObject(defultBucketName,fileName).getObjectContent();
    }
    /**
     * 上传
     * @param file
     * @return 文件链接
     */
    public static String upload(File file) {
        bosClient.putObject(defultBucketName,file.getName(),file);
        return baseUrl+file.getName();
    }
    public static String upload(InputStream fileIn,String fileName) {
        bosClient.putObject(defultBucketName,fileName,fileIn);
        return baseUrl+fileName;
    }

    /**
     * 文件是否存在
     * @param fileName
     * @return
     */
    public static boolean hasFile(String fileName) {
        return bosClient.doesObjectExist(defultBucketName, fileName);
    }

}
