package com.ruoyi.project.im.webserver.user.mapper;
import java.util.Map;

import com.ruoyi.project.im.webserver.base.dao.BaseDao;
import com.ruoyi.project.im.webserver.user.model.UserAccountEntity;

/**
 * 用户帐号
 * 
 * @author qiqiim
 * @email 1044053532@qq.com
 * @date 2017-11-27 09:38:52
 */
public interface UserAccountMapper extends BaseDao<UserAccountEntity> {
	public UserAccountEntity queryObjectByAccount(Map<String, Object> map);
}
