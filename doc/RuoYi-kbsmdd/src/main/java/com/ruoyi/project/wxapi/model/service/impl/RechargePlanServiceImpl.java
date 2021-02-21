package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.RechargePlan;
import com.ruoyi.project.wxapi.model.mapper.RechargePlanMapper;
import com.ruoyi.project.wxapi.model.service.IRechargePlanService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
@Service
public class RechargePlanServiceImpl extends ServiceImpl<RechargePlanMapper, RechargePlan> implements IRechargePlanService {

    @Override
    public RechargePlan getByWxAppId(Integer wxappId) {
        return this.getBaseMapper().getByWxAppId(wxappId);
    }

    @Override
    public List<RechargePlan> selectByWxAppId(Integer wxappId) {
        return this.getBaseMapper().selectByWxAppId(wxappId);
    }
}
