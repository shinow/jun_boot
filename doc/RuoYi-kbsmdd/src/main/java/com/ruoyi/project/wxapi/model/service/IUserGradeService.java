package com.ruoyi.project.wxapi.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.wxapi.model.bean.UserGrade;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-26
 */
public interface IUserGradeService extends IService<UserGrade> {

    UserGrade getByUserGradeId(Integer userGradeId);

    List<UserGrade> selectByWxAppId(Integer wxapp_id);
}
