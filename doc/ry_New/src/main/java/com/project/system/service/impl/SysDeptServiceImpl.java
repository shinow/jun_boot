package com.project.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.common.base.BaseServiceImpl;
import com.project.common.config.Global;
import com.project.common.utils.TimeUuidUtil;
import com.project.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.common.annotation.DataScope;
import com.project.common.constant.UserConstants;
import com.project.common.utils.StringUtils;
import com.project.system.domain.SysDept;
import com.project.system.domain.SysRole;
import com.project.system.mapper.SysDeptMapper;
import com.project.system.service.ISysDeptService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 部门管理 服务实现
 *
 * @author lws
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDeptServiceImpl> implements ISysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 查询部门管理数据
     *
     * @return 部门信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<SysDept> queryDeptList(SysDept dept,String delFlag) {
        dept.setDelFlag(delFlag);
        return deptMapper.selectDeptList(dept);
    }
    /**
     * 查询部门管理树
     *
     * @return 所有部门信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree(String notUnderJbdm) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        SysDept dept = new SysDept();
        dept.getParams().put("notUnderJbdm",notUnderJbdm);
        List<SysDept> deptList = getThis().queryDeptList(dept,"0");
        trees = getTrees(deptList, false, null);
        return trees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Map<String, Object>> roleDeptTreeData(SysRole role) {
        Long roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysDept> deptList = getThis().queryDeptList(new SysDept(),"0");
        if (StringUtils.isNotNull(roleId)) {
            List<String> jdbmList = deptMapper.selectRoleJbdm(roleId);
            trees = getTrees(deptList, true, jdbmList);
        } else {
            trees = getTrees(deptList, false, null);
        }
        return trees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param isCheck      是否需要选中
     * @param jdbmList      角色关联的部门的jbdm
     * @return
     */
    public List<Map<String, Object>> getTrees(List<SysDept> deptList, boolean isCheck, List<String> jdbmList) {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysDept dept : deptList) {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", dept.getDeptId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getDeptName());
                deptMap.put("title", dept.getDeptName());
                deptMap.put("jbdm",dept.getJbdm());
                deptMap.put("checked", false);
                if (isCheck) {
                    for(String jbdm : jdbmList){
                        if(dept.getJbdm().indexOf(jbdm)==0 || jbdm.indexOf(dept.getJbdm())==0){
                            deptMap.put("checked",true);
                            break;
                        }
                    }
                }
                trees.add(deptMap);
            }
        }
        return trees;
    }

    /**
     * 查询部门人数
     *
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long deptId,Long parentId,String delFlag) {
        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setDelFlag(delFlag);
        dept.setParentId(parentId);
        return deptMapper.selectDeptCount(dept);
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept) {
        dept.setDeptId(TimeUuidUtil.get16UUID());
        dept.setCreateBy(ShiroUtils.getLoginName());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(SysDept dept) {
        return deptMapper.updateDept(dept);
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long deptId) {
        return deptMapper.selectDeptById(deptId);
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param deptId 部门id
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(String deptName,Long deptId) {
        deptId = StringUtils.isNull(deptId) ? -1L : deptId;
        SysDept info = deptMapper.checkDeptNameUnique(deptName, null);
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    /**
     * 修改上级部门
     *
     * @param deptId   修改的部门
     * @param parentId 要修改的上级部门id
     * @return
     */
    @Transactional
    @Override
    public int updateParent(Long deptId, Long parentId) {
        //1.分别获取当前部门修改前后的jbdm
        String newJbdm = deptMapper.generateNewJbdmByParentId(parentId);
        String oldJbdm = deptMapper.selectDeptById(deptId).getJbdm();
        //2.更新上级id
        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setParentId(parentId);
        deptMapper.updateDept(dept);

        //3.更新当前部门以及所有下级的jbdm
        dept.setParentId(null);
        List<SysDept> depts = deptMapper.selectDeptList(dept);
        for (SysDept dept2:depts){
            dept2.setJbdm(newJbdm+dept2.getJbdm().substring(oldJbdm.length()));
        }
        return deptMapper.updateJbdmChildren(depts);
    }

    /**
     * 检查该部门能否继续添加下级(直接下级部门数量（包括已删除）达到900将不可添加)
     * @param parentId
     * @return
     *      1：可添加；
     *      2：jbdm已满，但有间隔（需调用adjustDeptJbdm释放间隔）；
     *      3:已达900上限，神仙来了都没办法
     */
    @Override
    public int checkDeptCanAdd(Long parentId) {
        String maxJbdm = deptMapper.selectMaxJbdmUnderParentId(parentId);
        if(maxJbdm == null){
            return 1;
        }
        if(!maxJbdm.substring(maxJbdm.length()-3).equals("999")){
            return 1;
        }
        int underDeptsNum = this.selectDeptCount(null,parentId,null);
        if(underDeptsNum<900){
            return 2;
        }
        return 3;
    }

    /**
     * 调整jbdm，使该数据连续，同等长度的jbdm没有间隔（为了保持同级部门最多900）
     * @param deptId
     * @return
     */
    @Transactional
    @Override
    public int adjustDeptJbdm(Long deptId) {
        if (deptId==null||deptId==0){
            deptId = Global.ROOT_DEPT_ID;
        }
        //1.查询该部门下的所有部门
        SysDept param = new SysDept();
        param.setDeptId(deptId);
        List<SysDept> list = deptMapper.selectDeptListNotIncludeCurrent(param);
        //2.清空jbdm，不然影响重新生成
        deptMapper.cleanJbdmUnderDeptId(deptId);
        //3.重新生成jbdm，并即刻修改数据库，不然影响下个或下级生成
        for (SysDept dept:list){
            dept.setJbdm(deptMapper.generateNewJbdmByParentId(dept.getParentId()));
            deptMapper.updateDept(dept);
        }
        return 1;
    }

    /**
     * 获取当前root部门
     *
     * @param dept
     * @return
     */
    @Override
    @DataScope(tableAlias = "d")
    public SysDept selectRootDept(SysDept dept,String delFlag) {
        dept.setDelFlag(delFlag);
        return deptMapper.selectRootDept(dept);
    }

}
