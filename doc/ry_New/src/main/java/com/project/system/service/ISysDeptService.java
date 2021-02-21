package com.project.system.service;

import java.util.List;
import java.util.Map;

import com.project.system.domain.SysDept;
import com.project.system.domain.SysRole;

/**
 * 部门管理 服务层
 *
 * @author lws
 */
public interface ISysDeptService {
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<SysDept> queryDeptList(SysDept dept,String delFlag);

    /**
     * 查询部门管理树
     * @param notUnderJbdm 不包含该jbdm以及下级
     * @return 所有部门信息
     */
    public List<Map<String, Object>> selectDeptTree(String notUnderJbdm);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> roleDeptTreeData(SysRole role);

    /**
     * 查询部门人数
     *
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Long deptId,Long parentId,String delFlag);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

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
     * @param deptId 部门id
     * @return 结果
     */
    public String checkDeptNameUnique(String deptName,Long deptId);

    /**
     * 修改上级部门(该方法重复使用将导致jbdm不连续)
     * @param deptId 修改的部门
     * @param parentId 要修改的上级部门id
     * @return
     */
    public int updateParent(Long deptId,Long parentId);

    /**
     * 检查该部门能否继续添加下级(直接下级部门数量（包括已删除）达到900将不可添加)
     * @param parentId 上级部门id
     * @return
     *      1：可添加；
     *      2：jbdm已满，但有间隔（需调用adjustDeptJbdm释放间隔）；
     *      3:已达900上限，神仙来了都没办法
     */
    public int checkDeptCanAdd(Long parentId);

    /**
     * 调整jbdm，使该数据连续，同等长度的jbdm没有间隔（为了保持同级部门最多900个）
     * 该处理相对耗时较长
     * @param deptId
     * @return
     */
    public int adjustDeptJbdm(Long deptId);
    /**
     * 获取当前root部门
     * @param dept
     * @return
     */
    public SysDept selectRootDept(SysDept dept,String delFlag);
}
