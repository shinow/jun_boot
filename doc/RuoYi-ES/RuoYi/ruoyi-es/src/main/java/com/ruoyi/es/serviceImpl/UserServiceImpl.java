package com.ruoyi.es.serviceImpl;

import com.ruoyi.es.bean.mysql.UserBean;
import com.ruoyi.es.mapper.UserMapper;
import com.ruoyi.es.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author japhet_jiu
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> selectUserBeanList(UserBean ub) {
        return userMapper.selectUserBeanList(ub);
    }

    @Override
    public List<UserBean> selectLikeUserBeanList(UserBean ub) {
        return userMapper.selectLikeUserBeanList(ub);
    }

    @Override
    public int updateById(UserBean userBean) {
        return userMapper.updateById(userBean);
    }

    @Override
    public int save(UserBean userBean) {
        return userMapper.save(userBean);
    }

    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }
}
