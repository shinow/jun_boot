package com.jeasyuicn.rbac.model.dao;

import com.jeasyuicn.rbac.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.model.dao</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年12月13日</li>
 * <li>@author     : ____′↘夏悸</li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<User, Long> {

    /**
     * 根据账号统计用户数
     *
     * @param account
     * @return
     */
    int countByAccount(String account);

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    User findFirstByAccount(String account);
}
