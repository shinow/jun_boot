package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.RechargePlan;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-21
 */
public interface IRechargePlanService extends IService<RechargePlan> {

    RechargePlan getByWxAppId(Integer wxappId);

    List<RechargePlan> selectByWxAppId(Integer wxappId);
}
