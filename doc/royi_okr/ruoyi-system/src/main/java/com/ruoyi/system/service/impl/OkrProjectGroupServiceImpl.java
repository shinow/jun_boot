package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrProjectGroupMapper;
import com.ruoyi.system.domain.OkrProjectGroup;
import com.ruoyi.system.service.IOkrProjectGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品项目组Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
@Service
public class OkrProjectGroupServiceImpl implements IOkrProjectGroupService
{
    @Autowired
    private OkrProjectGroupMapper okrProjectGroupMapper;

    /**
     * 查询产品项目组
     * 
     * @param projectCode 项目编号
     * @return 产品项目组
     */
    @Override
    public OkrProjectGroup selectOkrProjectGroupByCode(String projectCode)
    {
        return okrProjectGroupMapper.selectOkrProjectGroupByCode(projectCode);
    }

    /**
     * 查询group项目组织结构
     * @param groupId 组织标识
     * @return
     */
    @Override
    public OkrProjectGroup selectOkrProjectGroupById(Long groupId) {
        return okrProjectGroupMapper.selectOkrProjectGroupById(groupId);
    }

    /**
     * 查询产品项目组列表
     * 
     * @param okrProjectGroup 产品项目组
     * @return 产品项目组
     */
    @Override
    public List<OkrProjectGroup> selectOkrProjectGroupList(OkrProjectGroup okrProjectGroup)
    {
        return okrProjectGroupMapper.selectOkrProjectGroupList(okrProjectGroup);
    }

    @Override
    public String checkGroupNameUnique(OkrProjectGroup okrProjectGroup) {
        Long deptId = StringUtils.isNull(okrProjectGroup.getGroupId()) ? -1L : okrProjectGroup.getGroupId();
        OkrProjectGroup info = okrProjectGroupMapper.checkGroupNameUnique(okrProjectGroup.getGroupName(), okrProjectGroup.getParentId(),okrProjectGroup.getProjectCode());
        if (StringUtils.isNotNull(info) && info.getGroupId().longValue() != deptId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }


    @Override
    public List<Ztree> selectProjectGroupTree(OkrProjectGroup okrProjectGroup) {
        List<OkrProjectGroup> deptList = okrProjectGroupMapper.selectOkrProjectGroupList(okrProjectGroup);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }



    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<OkrProjectGroup> deptList)
    {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param groupList 项目组列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<OkrProjectGroup> groupList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (OkrProjectGroup group : groupList)
        {
            if (UserConstants.DEPT_NORMAL.equals(group.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(group.getGroupId());
                ztree.setpId(group.getParentId());
                ztree.setName(group.getGroupName());
                ztree.setTitle(group.getGroupName());
                if (isCheck)
                {
                    ztree.setChecked(roleDeptList.contains(group.getGroupId() + group.getGroupName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询产品项目组树形列表
     *
     * @param okrProjectGroup 产品项目组
     * @return 产品项目组
     */
    @Override
    public List<OkrProjectGroup> selectOkrProjectGroupTreeList(OkrProjectGroup okrProjectGroup) {
        return okrProjectGroupMapper.selectOkrProjectGroupTreeList(okrProjectGroup);
    }

    /**
     * 新增产品项目组
     * 
     * @param okrProjectGroup 产品项目组
     * @return 结果
     */
    @Override
    public int insertOkrProjectGroup(OkrProjectGroup okrProjectGroup)
    {
        okrProjectGroup.setCreateTime(DateUtils.getNowDate());
        return okrProjectGroupMapper.insertOkrProjectGroup(okrProjectGroup);
    }

    /**
     * 修改产品项目组
     * 
     * @param okrProjectGroup 产品项目组
     * @return 结果
     */
    @Override
    public int updateOkrProjectGroup(OkrProjectGroup okrProjectGroup)
    {
        okrProjectGroup.setUpdateTime(DateUtils.getNowDate());
        return okrProjectGroupMapper.updateOkrProjectGroup(okrProjectGroup);
    }

    /**
     * 删除产品项目组对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectGroupByIds(String ids)
    {
        return okrProjectGroupMapper.deleteOkrProjectGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品项目组信息
     * 
     * @param groupId 产品项目组ID
     * @return 结果
     */
    @Override
    public int deleteOkrProjectGroupById(Long groupId)
    {
        return okrProjectGroupMapper.deleteOkrProjectGroupById(groupId);
    }
}
