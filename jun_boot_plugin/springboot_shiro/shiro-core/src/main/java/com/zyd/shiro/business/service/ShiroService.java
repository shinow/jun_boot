/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.shiro.business.service;

import com.zyd.shiro.business.entity.User;

import java.util.Map;

/**
 * Shiro-权限相关的业务处理
 *
 * @author Wujun
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/25 14:37
 * @since 1.0
 */
public interface ShiroService {

    /**
     * 初始化权限
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    void reloadAuthorizingByUserId(User user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Long roleId);

}
