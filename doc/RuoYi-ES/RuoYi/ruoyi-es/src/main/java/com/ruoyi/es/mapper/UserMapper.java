package com.ruoyi.es.mapper;

import com.ruoyi.es.bean.mysql.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<UserBean> selectUserBeanList(UserBean ub);

    List<UserBean> selectLikeUserBeanList(UserBean ub);

    int updateById(UserBean userBean);

    int save(UserBean userBean);

    int deleteById(@Param("id") String id);
}
