package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.Wxapp;
import com.ruoyi.project.wxapi.model.mapper.WxappMapper;
import com.ruoyi.project.wxapi.model.service.IWxappService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
@Service
public class WxappServiceImpl extends ServiceImpl<WxappMapper, Wxapp> implements IWxappService {

    @Override
    public Wxapp selectByWxAppId(Integer wxapp_id) {
        return this.getBaseMapper().selectByWxAppId(wxapp_id);
    }
}
