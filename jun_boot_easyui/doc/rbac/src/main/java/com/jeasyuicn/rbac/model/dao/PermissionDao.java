package com.jeasyuicn.rbac.model.dao;

import com.jeasyuicn.rbac.model.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.model.dao</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年11月14日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Repository
public interface PermissionDao extends CrudRepository<Permission, Long> {

    /**
     * 获取根节点
     *
     * @return
     */
    List<Permission> findAllByParentIsNull();

    /**
     * 根据父节点找子节点
     *
     * @param parent
     * @return
     */
    List<Permission> findAllByParent(Permission parent);

    Set<Permission> findAllByEnableOrderByWeightDesc(boolean enable);
}
