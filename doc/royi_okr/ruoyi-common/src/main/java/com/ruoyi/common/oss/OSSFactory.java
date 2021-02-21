/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruoyi.common.oss;

import com.ruoyi.common.config.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author xiaoshuai2233@sina.com
 * @email fei6751803@163.com
 */
@Configuration
@Service
public class OSSFactory {


    @Value("${oss.aliyunDomain}")
    private String aliyunDomain;

    @Value("${oss.aliyunPrefix}")
    private String aliyunPrefix;

    @Value("${oss.aliyunEndPoint}")
    private String aliyunEndPoint;

    @Value("${oss.aliyunAccessKeyId}")
    private String aliyunAccessKeyId;

    @Value("${oss.aliyunAccessKeySecret}")
    private String aliyunAccessKeySecret;

    @Value("${oss.aliyunBucketName}")
    private String aliyunBucketName;


    public CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = new CloudStorageConfig();
        config.setAliyunAccessKeyId(aliyunAccessKeyId);
        config.setAliyunAccessKeySecret(aliyunAccessKeySecret);
        config.setAliyunBucketName(aliyunBucketName);
        config.setAliyunDomain(aliyunDomain);
        config.setAliyunEndPoint(aliyunEndPoint);
        config.setAliyunPrefix(aliyunPrefix);
        return new AliyunCloudStorageService(config);
    }

}
