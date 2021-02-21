package com.ruoyi.mq.service;

import com.ruoyi.mq.bean.LoginLog;

import java.util.List;

/**
 * 登录日志Service接口
 * 
 * @author japhet_jiu
 * @date 2020-05-27
 */
public interface ILoginLogService 
{
    /**
     * 查询登录日志
     * 
     * @param loginId 登录日志ID
     * @return 登录日志
     */
    public LoginLog selectLoginLogById(Long loginId);

    /**
     * 查询登录日志列表
     * 
     * @param loginLog 登录日志
     * @return 登录日志集合
     */
    public List<LoginLog> selectLoginLogList(LoginLog loginLog);

    /**
     * 新增登录日志
     * 
     * @param loginLog 登录日志
     * @return 结果
     */
    public int insertLoginLog(LoginLog loginLog);

    /**
     * 修改登录日志
     * 
     * @param loginLog 登录日志
     * @return 结果
     */
    public int updateLoginLog(LoginLog loginLog);

    /**
     * 批量删除登录日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLoginLogByIds(String ids);

    /**
     * 删除登录日志信息
     * 
     * @param loginId 登录日志ID
     * @return 结果
     */
    public int deleteLoginLogById(Long loginId);
}
