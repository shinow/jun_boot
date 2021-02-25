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
package com.alipay.sofa.runtime.integration.base;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.beans.impl.SampleServiceImpl;
import com.alipay.sofa.runtime.beans.service.SampleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wujun
 * @since 3.1.1
 */
@Configuration
@ConditionalOnProperty(name = "multiSofaReference", havingValue = "true")
public class MultiSofaReferenceConfiguration {
    @Bean
    @SofaService
    SampleService sampleService() {
        return new SampleServiceImpl("sampleService");
    }

    @Bean("multiReference")
    TestService service() {
        return new TestService() {
        };
    }

    @Bean("multiReference")
    TestService service(@Value("$spring.application.name") String appName,
                        @SofaReference SampleService service) {
        return new TestService() {
        };
    }

    public static interface TestService {
    }
}