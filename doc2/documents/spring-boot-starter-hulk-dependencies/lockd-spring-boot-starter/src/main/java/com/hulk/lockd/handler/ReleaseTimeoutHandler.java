package com.hulk.lockd.handler;

import com.hulk.lockd.constant.LockInfo;

/**
 * 获取锁超时的处理逻辑接口
 *
 * @author hulk
 * @since 2019/4/15
 **/
public interface ReleaseTimeoutHandler {

    void handle(LockInfo lockInfo);
}
