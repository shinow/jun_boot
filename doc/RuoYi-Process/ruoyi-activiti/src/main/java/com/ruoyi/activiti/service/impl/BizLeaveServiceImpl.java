package com.ruoyi.activiti.service.impl;

import com.github.pagehelper.Page;
import com.ruoyi.activiti.domain.BizLeave;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.mapper.BizLeaveMapper;
import com.ruoyi.activiti.service.IBizLeaveService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假业务Service业务层处理
 *
 * @author Xianlu Tech
 * @date 2019-10-11
 */
@Service
@Transactional
public class BizLeaveServiceImpl implements IBizLeaveService {
    @Autowired
    private BizLeaveMapper bizLeaveMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private IProcessService processService;

    /**
     * 查询请假业务
     *
     * @param id 请假业务ID
     * @return 请假业务
     */
    @Override
    public BizLeaveVo selectBizLeaveById(Long id) {
        BizLeaveVo leave = bizLeaveMapper.selectBizLeaveById(id);
        SysUser sysUser = userMapper.selectUserByLoginName(leave.getApplyUser());
        if (sysUser != null) {
            leave.setApplyUserName(sysUser.getUserName());
        }
        return leave;
    }

    /**
     * 查询请假业务列表
     *
     * @param bizLeave 请假业务
     * @return 请假业务
     */
    @Override
    public List<BizLeaveVo> selectBizLeaveList(BizLeaveVo bizLeave) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        // PageHelper 仅对第一个 List 分页
        Page<BizLeaveVo> list = (Page<BizLeaveVo>) bizLeaveMapper.selectBizLeaveList(bizLeave);
        Page<BizLeaveVo> returnList = new Page<>();
        for (BizLeaveVo leave: list) {
            SysUser sysUser = userMapper.selectUserByLoginName(leave.getCreateBy());
            if (sysUser != null) {
                leave.setCreateUserName(sysUser.getUserName());
            }
            SysUser sysUser2 = userMapper.selectUserByLoginName(leave.getApplyUser());
            if (sysUser2 != null) {
                leave.setApplyUserName(sysUser2.getUserName());
            }
            // 当前环节
            if (StringUtils.isNotBlank(leave.getInstanceId())) {
                List<Task> taskList = taskService.createTaskQuery()
                        .processInstanceId(leave.getInstanceId())
//                        .singleResult();
                        .list();    // 例如请假会签，会同时拥有多个任务
                if (!CollectionUtils.isEmpty(taskList)) {
                    TaskEntityImpl task = (TaskEntityImpl) taskList.get(0);
                    leave.setTaskId(task.getId());
                    if (task.getSuspensionState() == 2) {
                        leave.setTaskName("已挂起");
                        leave.setSuspendState("2");
                    } else {
                        leave.setTaskName(task.getName());
                        leave.setSuspendState("1");
                    }
                } else {
                    // 已办结或者已撤销
                    leave.setTaskName("已结束");
                }
            } else {
                leave.setTaskName("未启动");
            }
            returnList.add(leave);
        }
        returnList.setTotal(CollectionUtils.isEmpty(list) ? 0 : list.getTotal());
        returnList.setPageNum(pageNum);
        returnList.setPageSize(pageSize);
        return returnList;
    }

    /**
     * 新增请假业务
     *
     * @param bizLeave 请假业务
     * @return 结果
     */
    @Override
    public int insertBizLeave(BizLeaveVo bizLeave) {
        bizLeave.setCreateBy(ShiroUtils.getLoginName());
        bizLeave.setCreateTime(DateUtils.getNowDate());
        return bizLeaveMapper.insertBizLeave(bizLeave);
    }

    /**
     * 修改请假业务
     *
     * @param bizLeave 请假业务
     * @return 结果
     */
    @Override
    public int updateBizLeave(BizLeaveVo bizLeave) {
        bizLeave.setUpdateTime(DateUtils.getNowDate());
        return bizLeaveMapper.updateBizLeave(bizLeave);
    }

    /**
     * 删除请假业务对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveByIds(String ids) {
        return bizLeaveMapper.deleteBizLeaveByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请假业务信息
     *
     * @param id 请假业务ID
     * @return 结果
     */
    @Override
    public int deleteBizLeaveById(Long id) {
        return bizLeaveMapper.deleteBizLeaveById(id);
    }

    /**
     * 启动流程
     * @param entity
     * @param applyUserId
     * @return
     */
    @Override
    public ProcessInstance submitApply(BizLeaveVo entity, String applyUserId, String key, Map<String, Object> variables) {
        entity.setApplyUser(applyUserId);
        entity.setApplyTime(DateUtils.getNowDate());
        entity.setUpdateBy(applyUserId);
        bizLeaveMapper.updateBizLeave(entity);
        String businessKey = entity.getId().toString(); // 实体类 ID，作为流程的业务 key

        ProcessInstance processInstance = processService.submitApply(applyUserId, businessKey, entity.getTitle(), entity.getReason(), key, variables);

        String processInstanceId = processInstance.getId();
        entity.setInstanceId(processInstanceId); // 建立双向关系
        bizLeaveMapper.updateBizLeave(entity);

        return processInstance;
    }

    /**
     * 查询待办任务
     */
    public Page<BizLeaveVo> findTodoTasks(BizLeaveVo leave, String userId) {
        // 手动分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<BizLeaveVo> list = new Page<>();

        List<BizLeaveVo> results = new ArrayList<>();
        List<Task> tasks = processService.findTodoTasks(userId, leave.getType());
        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            TaskEntityImpl taskImpl = (TaskEntityImpl) task;
            String processInstanceId = taskImpl.getProcessInstanceId();
            // 条件过滤 1
            if (StringUtils.isNotBlank(leave.getInstanceId()) && !leave.getInstanceId().equals(processInstanceId)) {
                continue;
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();
            BizLeaveVo leave2 = bizLeaveMapper.selectBizLeaveById(new Long(businessKey));
            // 条件过滤 2
            if (StringUtils.isNotBlank(leave.getType()) && !leave.getType().equals(leave2.getType())) {
                continue;
            }
            leave2.setTaskId(taskImpl.getId());
            if (taskImpl.getSuspensionState() == 2) {
                leave2.setTaskName("已挂起");
            } else {
                leave2.setTaskName(taskImpl.getName());
            }
            SysUser sysUser = userMapper.selectUserByLoginName(leave2.getApplyUser());
            leave2.setApplyUserName(sysUser.getUserName());
            results.add(leave2);
        }

        List<BizLeaveVo> tempList;
        if (pageNum != null && pageSize != null) {
            int maxRow = (pageNum - 1) * pageSize + pageSize > results.size() ? results.size() : (pageNum - 1) * pageSize + pageSize;
            tempList = results.subList((pageNum - 1) * pageSize, maxRow);
            list.setTotal(results.size());
            list.setPageNum(pageNum);
            list.setPageSize(pageSize);
        } else {
            tempList = results;
        }

        list.addAll(tempList);

        return list;
    }

    /**
     * 查询已办列表
     * @param bizLeave
     * @param userId
     * @return
     */
    @Override
    public Page<BizLeaveVo> findDoneTasks(BizLeaveVo bizLeave, String userId) {
        // 手动分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<BizLeaveVo> list = new Page<>();

        List<BizLeaveVo> results = new ArrayList<>();
        List<HistoricTaskInstance> hisList = processService.findDoneTasks(userId, bizLeave.getType());
        // 根据流程的业务ID查询实体并关联
        for (HistoricTaskInstance instance : hisList) {
            String processInstanceId = instance.getProcessInstanceId();
            // 条件过滤 1
            if (StringUtils.isNotBlank(bizLeave.getInstanceId()) && !bizLeave.getInstanceId().equals(processInstanceId)) {
                continue;
            }
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();
            BizLeaveVo leave2 = bizLeaveMapper.selectBizLeaveById(new Long(businessKey));
            BizLeaveVo newLeave = new BizLeaveVo();
            BeanUtils.copyProperties(leave2, newLeave);
            // 条件过滤 2
            if (StringUtils.isNotBlank(bizLeave.getType()) && !bizLeave.getType().equals(leave2.getType())) {
                continue;
            }
            newLeave.setTaskId(instance.getId());
            newLeave.setTaskName(instance.getName());
            newLeave.setDoneTime(instance.getEndTime());
            SysUser sysUser = userMapper.selectUserByLoginName(leave2.getApplyUser());
            newLeave.setApplyUserName(sysUser.getUserName());
            results.add(newLeave);
        }

        List<BizLeaveVo> tempList;
        if (pageNum != null && pageSize != null) {
            int maxRow = (pageNum - 1) * pageSize + pageSize > results.size() ? results.size() : (pageNum - 1) * pageSize + pageSize;
            tempList = results.subList((pageNum - 1) * pageSize, maxRow);
            list.setTotal(results.size());
            list.setPageNum(pageNum);
            list.setPageSize(pageSize);
        } else {
            tempList = results;
        }

        list.addAll(tempList);

        return list;
    }

}
