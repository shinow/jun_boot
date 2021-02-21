package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.RechargePlan;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
public interface RechargePlanMapper extends BaseMapper<RechargePlan> {

    RechargePlan getByRechargePlanId(Integer rechargePlanId);

    RechargePlan getByWxAppId(Integer wxappId);

    List<RechargePlan> selectByWxAppId(Integer wxappId);
}
