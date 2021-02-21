package com.ruoyi.project.im.webserver.user.mapper;
import java.util.List;

import com.ruoyi.project.im.webserver.base.dao.BaseDao;
import com.ruoyi.project.im.webserver.user.model.ImFriendUserData;
import com.ruoyi.project.im.webserver.user.model.UserDepartmentEntity;

/**
 * 部门
 * 
 * @author qiqiim
 * @email 1044053532@qq.com
 * @date 2017-11-27 09:38:52
 */
public interface UserDepartmentMapper extends BaseDao<UserDepartmentEntity> {
	
	public List<ImFriendUserData> queryGroupAndUser(); 
}
