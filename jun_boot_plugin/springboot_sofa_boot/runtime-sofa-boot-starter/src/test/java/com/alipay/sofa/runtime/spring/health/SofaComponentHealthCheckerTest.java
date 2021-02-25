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
package com.alipay.sofa.runtime.spring.health;

import com.alipay.sofa.healthcheck.configuration.HealthCheckConstants;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wujun
 * @since 2.4.10
 */
public class SofaComponentHealthCheckerTest {

    private ConfigurableApplicationContext applicationContext;

    @After
    public void closeContext() {
        this.applicationContext.close();
    }

    @Test
    public void testDefaultConfig() {
        SpringApplication springApplication = new SpringApplication(EmptyConfiguration.class);
        this.applicationContext = springApplication.run(new String[] {});
        SofaComponentHealthChecker sofaComponentHealthChecker = applicationContext
            .getBean(SofaComponentHealthChecker.class);
        Assert.assertEquals(HealthCheckConstants.SOFABOOT_COMPONENT_CHECK_RETRY_DEFAULT_COUNT,
            sofaComponentHealthChecker.getRetryCount());
        Assert.assertEquals(HealthCheckConstants.SOFABOOT_COMPONENT_CHECK_RETRY_DEFAULT_INTERVAL,
            sofaComponentHealthChecker.getRetryTimeInterval());
    }

    @Test
    public void testCustomConfig() {
        int customRetryCount = 10;
        int customRetryInterval = 30;
        Map<String, Object> properties = new HashMap<>();
        properties.put(HealthCheckConstants.SOFABOOT_COMPONENT_CHECK_RETRY_COUNT, customRetryCount);
        properties.put(HealthCheckConstants.SOFABOOT_COMPONENT_CHECK_RETRY_INTERVAL,
            customRetryInterval);
        SpringApplication springApplication = new SpringApplication(EmptyConfiguration.class);
        springApplication.setDefaultProperties(properties);
        this.applicationContext = springApplication.run(new String[] {});
        SofaComponentHealthChecker sofaComponentHealthChecker = applicationContext
            .getBean(SofaComponentHealthChecker.class);
        Assert.assertEquals(customRetryCount, sofaComponentHealthChecker.getRetryCount());
        Assert.assertEquals(customRetryInterval, sofaComponentHealthChecker.getRetryTimeInterval());
    }

    @EnableAutoConfiguration
    @Configuration
    static class EmptyConfiguration {
    }

}