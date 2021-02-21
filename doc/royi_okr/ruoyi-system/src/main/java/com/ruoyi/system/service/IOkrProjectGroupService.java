package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.OkrProjectGroup;
import com.ruoyi.system.domain.SysDept;

/**
 * 产品项目组Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
public interface IOkrProjectGroupService 
{
    /**
     * 查询产品项目组
     * 
     * @param projectCode 项目编码
     * @return 产品项目组
     */
    public OkrProjectGroup selectOkrProjectGroupByCode(String projectCode);


    /**
     * 查询产品项目组
     *
     * @param groupId 组织标识
     * @return 产品项目组
     */
    public OkrProjectGroup selectOkrProjectGroupById(Long groupId);


    /**
     * 查询产品项目组列表
     * 
     * @param okrProjectGroup 产品项目组
     * @return 产品项目组集合
     */
    public List<OkrProjectGroup> selectOkrProjectGroupList(OkrProjectGroup okrProjectGroup);



    /**
     * 校验项目组名称是否唯一
     *
     * @param okrProjectGroup 项目组信息
     * @return 结果
     */
    public String checkGroupNameUnique(OkrProjectGroup okrProjectGroup);

    /**
     * 查询项目组管理树
     *
     * @param okrProjectGroup 项目组信息
     * @return 所有部门信息
     */
    public List<Ztree> selectProjectGroupTree(OkrProjectGroup okrProjectGroup);
    /**
     * 查询产品项目组树列表
     *
     * @param okrProjectGroup 产品项目组
     * @return 产品项目组集合
     */
    public List<OkrProjectGroup> selectOkrProjectGroupTreeList(OkrProjectGroup okrProjectGroup);

    /**
     * 新增产品项目组
     * 
     * @param okrProjectGroup 产品项目组
     * @return 结果
     */
    public int insertOkrProjectGroup(OkrProjectGroup okrProjectGroup);

    /**
     * 修改产品项目组
     * 
     * @param okrProjectGroup 产品项目组
     * @return 结果
     */
    public int updateOkrProjectGroup(OkrProjectGroup okrProjectGroup);

    /**
     * 批量删除产品项目组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectGroupByIds(String ids);

    /**
     * 删除产品项目组信息
     * 
     * @param groupId 产品项目组ID
     * @return 结果
     */
    public int deleteOkrProjectGroupById(Long groupId);
}
