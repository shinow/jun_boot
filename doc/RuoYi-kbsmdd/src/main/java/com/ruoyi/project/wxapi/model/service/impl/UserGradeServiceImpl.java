package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.UserGrade;
import com.ruoyi.project.wxapi.model.mapper.UserGradeMapper;
import com.ruoyi.project.wxapi.model.service.IUserGradeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-26
 */
@Service
public class UserGradeServiceImpl extends ServiceImpl<UserGradeMapper, UserGrade> implements IUserGradeService {

    @Override
    public UserGrade getByUserGradeId(Integer userGradeId) {
        return this.getBaseMapper().getByUserGradeId(userGradeId);
    }

    @Override
    public List<UserGrade> selectByWxAppId(Integer wxappId) {
        return this.getBaseMapper().selectByWxAppId(wxappId);
    }
}
