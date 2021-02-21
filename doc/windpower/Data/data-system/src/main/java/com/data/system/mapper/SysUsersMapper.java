package com.data.system.mapper;

import java.util.List;

import com.data.system.domain.SysUsers;

/**
 * 增删改查测试Mapper接口
 * 
 * @author ruoyi
 * @date 2019-12-02
 */
public interface SysUsersMapper 
{
    /**
     * 查询增删改查测试
     * 
     * @param id 增删改查测试ID
     * @return 增删改查测试
     */
    public SysUsers selectSysUsersById(Long id);

    /**
     * 查询增删改查测试列表
     * 
     * @param sysUsers 增删改查测试
     * @return 增删改查测试集合
     */
    public List<SysUsers> selectSysUsersList(SysUsers sysUsers);

    /**
     * 新增增删改查测试
     * 
     * @param sysUsers 增删改查测试
     * @return 结果
     */
    public int insertSysUsers(SysUsers sysUsers);

    /**
     * 修改增删改查测试
     * 
     * @param sysUsers 增删改查测试
     * @return 结果
     */
    public int updateSysUsers(SysUsers sysUsers);

    /**
     * 删除增删改查测试
     * 
     * @param id 增删改查测试ID
     * @return 结果
     */
    public int deleteSysUsersById(Long id);

    /**
     * 批量删除增删改查测试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUsersByIds(String[] ids);
}
