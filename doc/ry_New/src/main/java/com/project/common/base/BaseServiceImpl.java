package com.project.common.base;

import com.project.framework.util.SpringUtils;

public abstract class BaseServiceImpl<T> {

    /**
     * 为了保证在service层内部调用时，各个配置的aop依然生效，
     * 需由spring获取该对象后再调用
     * @return 从spring获取的当前实例
     */
    protected T getThis(){
        return (T)SpringUtils.getBean(this.getClass());
    }

}
