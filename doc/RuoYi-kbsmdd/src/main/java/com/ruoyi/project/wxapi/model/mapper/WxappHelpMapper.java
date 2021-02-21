package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.WxappHelp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
public interface WxappHelpMapper extends BaseMapper<WxappHelp> {

    List<WxappHelp> selectByWxappId(Integer wxappId);
}
