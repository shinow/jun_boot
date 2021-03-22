package org.springrain.alipay.service.impl;

import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.service.BaseServiceImpl;

import javax.annotation.Resource;

public class BaseSpringrainAliPayServiceImpl extends BaseServiceImpl {

    @Resource
    IBaseJdbcDao baseSpringrainDao;

    public BaseSpringrainAliPayServiceImpl() {
    }

    @Override
    public IBaseJdbcDao getBaseDao() {
        return baseSpringrainDao;
    }
}
