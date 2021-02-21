package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.Wxapp;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-01
 */
public interface WxappMapper extends BaseMapper<Wxapp> {

    Wxapp selectByWxAppId(Integer wxapp_id);
}
