package com.ruoyi.mq.service.serviceImpl;

import java.util.List;

import com.ruoyi.mq.bean.LoginLog;
import com.ruoyi.mq.mapper.LoginLogMapper;
import com.ruoyi.mq.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 登录日志Service业务层处理
 * 
 * @author japhet_jiu
 * @date 2020-05-27
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService
{
    @Autowired
    private LoginLogMapper loginLogMapper;

    /**
     * 查询登录日志
     * 
     * @param loginId 登录日志ID
     * @return 登录日志
     */
    @Override
    public LoginLog selectLoginLogById(Long loginId)
    {
        return loginLogMapper.selectLoginLogById(loginId);
    }

    /**
     * 查询登录日志列表
     * 
     * @param loginLog 登录日志
     * @return 登录日志
     */
    @Override
    public List<LoginLog> selectLoginLogList(LoginLog loginLog)
    {
        return loginLogMapper.selectLoginLogList(loginLog);
    }

    /**
     * 新增登录日志
     * 
     * @param loginLog 登录日志
     * @return 结果
     */
    @Override
    public int insertLoginLog(LoginLog loginLog)
    {
        return loginLogMapper.insertLoginLog(loginLog);
    }

    /**
     * 修改登录日志
     * 
     * @param loginLog 登录日志
     * @return 结果
     */
    @Override
    public int updateLoginLog(LoginLog loginLog)
    {
        return loginLogMapper.updateLoginLog(loginLog);
    }

    /**
     * 删除登录日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLoginLogByIds(String ids)
    {
        return loginLogMapper.deleteLoginLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除登录日志信息
     * 
     * @param loginId 登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLoginLogById(Long loginId)
    {
        return loginLogMapper.deleteLoginLogById(loginId);
    }
}
