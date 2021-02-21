package com.project.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.project.common.utils.DateUtils;
import com.project.common.utils.StringUtils;
import com.project.system.domain.SysUserOnline;
import com.project.system.mapper.SysUserOnlineMapper;

/**
 * 在线用户 服务层处理
 *
 * @author lws
 */
@Component
public class SysUserOnlineServiceImpl {
    @Autowired
    private SysUserOnlineMapper userOnlineDao;

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public SysUserOnline selectOnlineById(String sessionId) {
        return userOnlineDao.selectOnlineById(sessionId);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteOnlineById(String sessionId) {
        SysUserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline)) {
            userOnlineDao.deleteOnlineById(sessionId);
        }
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            SysUserOnline userOnline = selectOnlineById(sessionId);
            if (StringUtils.isNotNull(userOnline)) {
                userOnlineDao.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    public void saveOnline(SysUserOnline online) {
        userOnlineDao.saveOnline(online);
    }

    /**
     * 查询会话集合
     *
     * @param pageUtilEntity 分页参数
     */
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) {
        return userOnlineDao.selectUserOnlineList(userOnline);
    }

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId) {
        userOnlineDao.deleteOnlineById(sessionId);
    }

    /**
     * 查询会话集合
     *
     * @param online 会话信息
     */
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineDao.selectOnlineByExpired(lastAccessTime);
    }
}
