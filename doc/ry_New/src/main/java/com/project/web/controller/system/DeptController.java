package com.project.web.controller.system;

import java.util.List;
import java.util.Map;

import com.project.common.config.Global;
import com.project.framework.manager.AsyncManager;
import com.project.framework.manager.factory.AsyncFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.common.utils.StringUtils;
import com.project.common.annotation.Log;
import com.project.common.base.AjaxResult;
import com.project.common.enums.BusinessType;
import com.project.framework.util.ShiroUtils;
import com.project.system.domain.SysDept;
import com.project.system.domain.SysRole;
import com.project.system.service.ISysDeptService;
import com.project.web.core.base.BaseController;

/**
 * 部门信息
 *
 * @author lws
 */
@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept(ModelMap modelMap) {
        SysDept dept = deptService.selectRootDept(new SysDept(),"0");
        if(dept!=null){
            modelMap.addAttribute("rootIdValue",dept.getParentId());
        }
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @GetMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept,String delFlag) {
        List<SysDept> deptList = deptService.queryDeptList(dept,delFlag);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add")
    public String add(Long parentId, ModelMap mmap) {
        if(parentId==null){
            parentId = Global.ROOT_DEPT_ID;
        }
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysDept dept) {
        AjaxResult ar = checkDeptCanAdd(dept.getParentId());
        if(ar!=null){
            return ar;
        }
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && Global.ROOT_DEPT_ID == deptId) {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysDept dept) {
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @PostMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId) {
        if (deptService.selectDeptCount(null,deptId,"0") > 0) {
            return error(1, "存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId)) {
            return error(1, "部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }
    @Log(title = "部门管理-修改上级", businessType = BusinessType.UPDATE)
    @PostMapping("/editParent/{deptId}/{parentId}")
    @ResponseBody
    public AjaxResult editParent(@PathVariable("deptId")Long deptId,@PathVariable("parentId")Long parentId) {
        AjaxResult ar = checkDeptCanAdd(parentId);
        if(ar!=null){
            return ar;
        }
        return toAjax(deptService.updateParent(deptId,parentId));
    }
    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept dept) {
        return deptService.checkDeptNameUnique(dept.getDeptName(),dept.getDeptId());
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree")
    public String selectDeptTree(Long deptId,String notUnderJbdm, ModelMap mmap) {
        if(deptId==null){
            deptId= Global.ROOT_DEPT_ID;
        }
        if(notUnderJbdm!=null){
            mmap.put("notUnderJbdm",notUnderJbdm);
        }
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData(String notUnderJbdm) {
        List<Map<String, Object>> tree = deptService.selectDeptTree(notUnderJbdm);
        return tree;
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Map<String, Object>> deptTreeData(SysRole role) {
        List<Map<String, Object>> tree = deptService.roleDeptTreeData(role);
        return tree;
    }

    private AjaxResult checkDeptCanAdd(Long parentId){
        int code = deptService.checkDeptCanAdd(parentId);
        if(code==2){
            if(deptService.selectDeptCount(null,parentId,null)>1000){
                //数据量过大时异步处理
                AsyncManager.me().execute(AsyncFactory.adjustDeptJbdmTask(parentId));
                return error(1, "同级部门数量已接近上限，后台正在释放空间，请稍候重新登录重试并慎重添加！");
            }else{
                //数据量少同步处理
                deptService.adjustDeptJbdm(parentId);
                return null;
            }
        }else if(code==3){
            return error(1, "同级部门数量达到上限，不能继续添加，请选择其它上级！");
        }
        return null;
    }
}
