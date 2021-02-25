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
package com.alipay.sofa.runtime.spi.log;

import com.alipay.sofa.common.log.LoggerSpaceManager;
import org.slf4j.Logger;

/**
 * @author Wujun
 */
public class SofaRuntimeLoggerFactory {
    /***
     * sofa runtime log space
     */
    public static final String SOFA_RUNTIME_LOG_SPACE = "com.alipay.sofa.runtime";

    /**
     * get Logger Object
     *
     * @param name
     * @return Logger Object
     */
    public static Logger getLogger(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return LoggerSpaceManager.getLoggerBySpace(name, SOFA_RUNTIME_LOG_SPACE);
    }

    /**
     * get Logger Object
     * @param klazz
     * @return Logger Object
     */
    public static Logger getLogger(Class klazz) {
        if (klazz == null) {
            return null;
        }
        return getLogger(klazz.getCanonicalName());
    }
}
