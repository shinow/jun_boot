package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OkrProjectUser;

/**
 * 项目组用户关系Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
public interface IOkrProjectUserService 
{
    /**
     * 查询项目组用户关系
     * 
     * @param id 项目组用户关系ID
     * @return 项目组用户关系
     */
    public OkrProjectUser selectOkrProjectUserById(Long id);

    /**
     * 查询项目组用户关系列表
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 项目组用户关系集合
     */
    public List<OkrProjectUser> selectOkrProjectUserList(OkrProjectUser okrProjectUser);



    /**
     * 查询项目组用户关系是否存在
     *
     * @param okrProjectUser 项目组用户关系
     * @return 项目组用户关系集合
     */
    public OkrProjectUser selectOkrProjectUserExist(OkrProjectUser okrProjectUser);

    /**
     * 新增项目组用户关系
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 结果
     */
    public int insertOkrProjectUser(OkrProjectUser okrProjectUser);

    /**
     * 修改项目组用户关系
     * 
     * @param okrProjectUser 项目组用户关系
     * @return 结果
     */
    public int updateOkrProjectUser(OkrProjectUser okrProjectUser);

    /**
     * 批量删除项目组用户关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectUserByIds(String ids);

    /**
     * 删除项目组用户关系信息
     * 
     * @param id 项目组用户关系ID
     * @return 结果
     */
    public int deleteOkrProjectUserById(Long id);
}
