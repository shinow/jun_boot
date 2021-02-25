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
package com.alipay.sofa.runtime.spring;

import com.alipay.sofa.runtime.api.event.ApplicationShutdownCallback;
import com.alipay.sofa.runtime.spi.component.SofaRuntimeManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;

/**
 * @author Wujun
 * @author Wujun
 * @since 2.5.0
 */
public class ApplicationShutdownCallbackPostProcessor implements MergedBeanDefinitionPostProcessor,
                                                     PriorityOrdered {

    private SofaRuntimeManager sofaRuntimeManager;

    public ApplicationShutdownCallbackPostProcessor(SofaRuntimeManager sofaRuntimeManager) {
        this.sofaRuntimeManager = sofaRuntimeManager;
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition,
                                                Class<?> beanType, String beanName) {
        // no process
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
                                                                               throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
                                                                              throws BeansException {
        if (bean instanceof ApplicationShutdownCallback) {
            sofaRuntimeManager.registerShutdownCallback((ApplicationShutdownCallback) bean);
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.LOWEST_PRECEDENCE;
    }
}