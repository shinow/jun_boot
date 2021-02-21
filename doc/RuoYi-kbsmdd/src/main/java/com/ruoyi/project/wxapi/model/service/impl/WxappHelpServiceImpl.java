package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.WxappHelp;
import com.ruoyi.project.wxapi.model.mapper.WxappHelpMapper;
import com.ruoyi.project.wxapi.model.service.IWxappHelpService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-23
 */
@Service
public class WxappHelpServiceImpl extends ServiceImpl<WxappHelpMapper, WxappHelp> implements IWxappHelpService {

    @Override
    public List<WxappHelp> selectByWxappId(Integer wxappId) {
        return this.getBaseMapper().selectByWxappId(wxappId);
    }
}
