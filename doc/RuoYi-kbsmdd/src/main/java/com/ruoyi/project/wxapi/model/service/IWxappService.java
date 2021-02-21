package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.Wxapp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
public interface IWxappService extends IService<Wxapp> {

    Wxapp selectByWxAppId(Integer wxapp_id);
}
