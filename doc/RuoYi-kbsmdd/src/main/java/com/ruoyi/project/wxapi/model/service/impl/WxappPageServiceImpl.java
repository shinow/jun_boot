package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.WxappPage;
import com.ruoyi.project.wxapi.model.mapper.WxappPageMapper;
import com.ruoyi.project.wxapi.model.service.IWxappPageService;
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
public class WxappPageServiceImpl extends ServiceImpl<WxappPageMapper, WxappPage> implements IWxappPageService {

    @Override
    public WxappPage selectByWxAppId(Integer wxapp_id) {
        return this.getBaseMapper().selectByWxAppId(wxapp_id);
    }
}
