package com.ruoyi.project.wxapi.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.wxapi.model.bean.UserGrade;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hnkb
 * @since 2020-07-26
 */
public interface UserGradeMapper extends BaseMapper<UserGrade> {

    UserGrade getByUserGradeId(Integer userGradeId);

    List<UserGrade> selectByWxAppId(Integer wxappId);
}
