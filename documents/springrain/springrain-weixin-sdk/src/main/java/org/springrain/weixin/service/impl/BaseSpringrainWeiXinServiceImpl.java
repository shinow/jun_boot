package org.springrain.weixin.service.impl;

import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.service.BaseServiceImpl;

import javax.annotation.Resource;

public class BaseSpringrainWeiXinServiceImpl extends BaseServiceImpl {

    @Resource
    IBaseJdbcDao baseSpringrainDao;

    public BaseSpringrainWeiXinServiceImpl() {
    }

    @Override
    public IBaseJdbcDao getBaseDao() {
        return baseSpringrainDao;
    }
}
