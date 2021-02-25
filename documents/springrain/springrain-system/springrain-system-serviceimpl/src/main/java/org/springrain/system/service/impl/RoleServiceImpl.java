package org.springrain.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.system.entity.Role;
import org.springrain.system.service.IRoleService;


/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2019-07-24 14:20:37
 */

@Service("roleService")
public class RoleServiceImpl extends BaseSpringrainServiceImpl implements IRoleService {


    @Override
    public String save(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Role role = (Role) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findRoleById_" + role.getId());
        return super.update(entity);
    }

    @Override
    public Role findRoleById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findRoleById_" + id;
        Role role = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, Role.class);
        if (role != null) {
            return role;
        }
        role = super.findById(id, Role.class);
        if (role == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, role);
        return role;
    }


}
