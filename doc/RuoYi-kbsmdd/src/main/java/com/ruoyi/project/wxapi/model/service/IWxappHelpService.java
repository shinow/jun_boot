package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.WxappHelp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
public interface IWxappHelpService extends IService<WxappHelp> {

    List<WxappHelp> selectByWxappId(Integer wxappId);
}
