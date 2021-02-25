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
package com.alipay.sofa.isle.util;

import com.alipay.sofa.isle.integration.IntegrationTest;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * @author Wujun
 */
public class ClassPathUtil {
    public static void addClassPathAtRuntime(String jarName) throws Exception {
        Enumeration<URL> urls = IntegrationTest.class.getClassLoader().getResources(jarName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            if (classLoader instanceof URLClassLoader) {
                Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
                method.setAccessible(true);
                method.invoke(classLoader, url);
            } else {
                Method method = classLoader.getClass().getDeclaredMethod(
                    "appendToClassPathForInstrumentation", String.class);
                method.setAccessible(true);
                method.invoke(classLoader, url.getFile());
            }
        }
    }
}
