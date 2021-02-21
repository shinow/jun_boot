package com.ruoyi.es.service;


import com.ruoyi.es.bean.mysql.UserBean;

import java.util.List;

/**
 * @author japhet_jiu
 * @version 1.0
 */
public interface UserService {
    List<UserBean> selectUserBeanList(UserBean ub);

    List<UserBean> selectLikeUserBeanList(UserBean ub);

    int updateById(UserBean userBean);

    int save(UserBean userBean);

    int deleteById(String id);
}
