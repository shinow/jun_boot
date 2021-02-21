package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrProjectGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 产品项目组Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-05-29
 */
public interface OkrProjectGroupMapper 
{
    /**
     * 查询产品项目组
     * 
     * @param projectCode 产品项目组ID
     * @return 产品项目组
     */
    public OkrProjectGroup selectOkrProjectGroupByCode(String projectCode);


    /**
     * 查询产品项目组
     *
     * @param groupId 项目组编号
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
     * @param deptName 项目组名称
     * @param parentId 父部门ID
     * @param projectCode 项目编号
     * @return 结果
     */
    public OkrProjectGroup checkGroupNameUnique(@Param("groupName") String deptName, @Param("parentId") Long parentId, @Param("projectCode") String projectCode);


    /**
     * 查询产品项目组树形列表
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
     * 删除产品项目组
     * 
     * @param groupId 产品项目组ID
     * @return 结果
     */
    public int deleteOkrProjectGroupById(Long groupId);

    /**
     * 批量删除产品项目组
     * 
     * @param groupIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrProjectGroupByIds(String[] groupIds);
}
