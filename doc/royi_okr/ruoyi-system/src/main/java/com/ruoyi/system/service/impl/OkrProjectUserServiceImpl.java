package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrProjectUserMapper;
import com.ruoyi.system.domain.OkrProjectUser;
import com.ruoyi.system.service.IOkrProjectUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 项目组用户关系Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Service
public class OkrProjectUserServiceImpl implements IOkrProjectUserService 
{
    @Autowired
    private OkrProjectUserMapper okrProjectUserMapper;

    /**
     * 查询项目组用户关系
     * 
     * @param id 项目组用户关系ID
     * @return 项目组用户关系
     */
    @Override
    public OkrProjectUser selectOkrProjectUserById(Long id)
    {
        return okrProjectUserMapper.selectOkrProjectUserById(id);
    }

    /**
     * 查询项目组用户关系列表
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 项目组用户关系
     */
    @Override
    public List<OkrProjectUser> selectOkrProjectUserList(OkrProjectUser okrProjectUser)
    {
        return okrProjectUserMapper.selectOkrProjectUserList(okrProjectUser);
    }

    /**
     * 查询项目组用户关系是否存在
     *
     * @param okrProjectUser 项目组用户关系
     * @return 项目组用户关系集合
     */
    @Override
    public OkrProjectUser selectOkrProjectUserExist(OkrProjectUser okrProjectUser) {
        return okrProjectUserMapper.selectOkrProjectUserExist(okrProjectUser);
    }

    /**
     * 新增项目组用户关系
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 结果
     */
    @Override
    public int insertOkrProjectUser(OkrProjectUser okrProjectUser)
    {
        return okrProjectUserMapper.insertOkrProjectUser(okrProjectUser);
    }

    /**
     * 修改项目组用户关系
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 结果
     */
    @Override
    public int updateOkrProjectUser(OkrProjectUser okrProjectUser)
    {
        return okrProjectUserMapper.updateOkrProjectUser(okrProjectUser);
    }

    /**
     * 删除项目组用户关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectUserByIds(String ids)
    {
        return okrProjectUserMapper.deleteOkrProjectUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目组用户关系信息
     * 
     * @param id 项目组用户关系ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectUserById(Long id)
    {
        return okrProjectUserMapper.deleteOkrProjectUserById(id);
    }
}
