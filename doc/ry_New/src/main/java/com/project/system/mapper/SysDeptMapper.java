package com.project.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.project.system.domain.SysDept;

/**
 * 部门管理 数据层
 *
 * @author lws
 */
public interface SysDeptMapper {
    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDeptCount(SysDept dept);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 获取当前root部门
     * @param dept
     * @return
     */
    public SysDept selectRootDept(SysDept dept);

    /**
     * 查询所有下级，不包含当前部门
     * @param dept
     * @return
     */
    public List<SysDept> selectDeptListNotIncludeCurrent(SysDept dept);
    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 批量修改jbdm
     * @param depts
     * @return
     */
    public int updateJbdmChildren(@Param("depts") List<SysDept> depts);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询jdbm
     *
     * @param roleId 角色ID
     * @return 部门列表
     */
    public List<String> selectRoleJbdm(Long roleId);

    /**
     * 直接下级的最大的jbdm
     * @param parentId
     * @return
     */
    public String selectMaxJbdmUnderParentId(Long parentId);


    /**
     * 根据部门的id算出该部门的直接下级的新的jbdm
     * @param parentId
     * @return
     */
    public String generateNewJbdmByParentId(Long parentId);

    /**
     * 清空jbdm（该部门的所有下级-不包含当前部门）
     * @param deptId
     * @return
     */
    public int cleanJbdmUnderDeptId(Long deptId);

}
