package com.jun.plugin.modules.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.plugin.common.constant.CoreConst;
import com.jun.plugin.common.exception.HtException;
import com.jun.plugin.common.util.PasswordHelper;
import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.util.StringUtils;
import com.jun.plugin.common.util.UUIDUtil;
import com.jun.plugin.common.vo.ResponseVo;
import com.jun.plugin.modules.sys.mapper.UserMapper;
import com.jun.plugin.modules.sys.mapper.UserRoleMapper;
import com.jun.plugin.modules.sys.model.SysUser;
import com.jun.plugin.modules.sys.model.SysUserRole;
import com.jun.plugin.modules.sys.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public SysUser getUserByUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		return userMapper.selectOneByExample(example);
	}

	@Override
	public int register(SysUser user) {
		return userMapper.insert(user);
	}

	@Override
	public void updateLastLoginTimeByUser(SysUser sysUser) {
		if (sysUser != null) {
			sysUser.setLastLoginTime(new Date());
			userMapper.updateByPrimaryKeySelective(sysUser);
		}
	}

	@Override
	public List<SysUser> listUsers(SysUser user) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(user.getUsername())) {
			criteria.andLike("username", user.getUsername());
		}
		if (StringUtils.isNotEmpty(user.getEmail())) {
			criteria.andLike("email", user.getEmail());
		}
		if (StringUtils.isNotEmpty(user.getPhone())) {
			criteria.andLike("phone", user.getPhone());
		}
		return userMapper.selectByExample(example);
	}

	@Override
	public SysUser getUserByUserId(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateUserByUserId(SysUser sysUser) {
		sysUser.setUpdateTime(new Date());
		return userMapper.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public int updateUserStatusByUserIds(List<String> userIds, Integer status) {
		if (StringUtils.isEmpty(userIds)) {
			return CoreConst.RESULT_FAIL_CODE;
		}
		if (status == null) {
			return CoreConst.RESULT_FAIL_CODE;
		}
		Map<String, Object> params = new HashMap<String, Object>(2);
		params.put("userIds", userIds);
		params.put("status", status);
		return userMapper.updateUserStatusByUserIds(params);
	}

	@Override
	public ResponseVo<?> addAssignRole(Long userId, Long[] roleIds) {
		try {
			if (userId == null) {
				return ResultUtil.error("??????????????????");
			}
			Example example = new Example(SysUserRole.class);
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("userId", userId);
			userRoleMapper.deleteByExample(example);
			// ??????????????????
			if (StringUtils.isEmpty(roleIds)) {
				return ResultUtil.success("??????????????????");
			}
			List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
			for (Long roleId : roleIds) {
				SysUserRole userRole = new SysUserRole();
				userRole.setUserRoleId(Long.valueOf(UUIDUtil.getUniqueIdByUUId()));
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				userRoleList.add(userRole);
			}
			int insertResult = userRoleMapper.insertList(userRoleList);
			if (insertResult == 0) {
				return ResultUtil.error("??????????????????");
			}
			return ResultUtil.success("??????????????????");
		} catch (Exception e) {
			return ResultUtil.error("??????????????????");
		}
	}

	@Override
	public String importUser(List<SysUser> userList, boolean updateSupport, String operName) {
		if (StringUtils.isEmpty(userList) || userList.size() == 0) {
			throw new HtException("?????????????????????????????????");
		}
		int successNum = 0;
		int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		for (SysUser user : userList) {
			try {
				// ??????????????????????????????
				SysUser u = this.getUserByUsername(user.getUsername());
				if (StringUtils.isNull(u)) {
					if(StringUtils.isEmpty(user.getPassword())) {
						user.setPassword("123456");
					}
					PasswordHelper.encryptPassword(user);
					userMapper.insert(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "????????? " + user.getUsername() + " ????????????");
				} else if (updateSupport) {
					userMapper.updateByPrimaryKeySelective(user);
					successNum++;
					successMsg.append("<br/>" + successNum + "????????? " + user.getUsername() + " ????????????");
				} else {
					failureNum++;
					failureMsg.append("<br/>" + failureNum + "????????? " + user.getUsername() + " ?????????");
				}
			} catch (Exception e) {
				failureNum++;
				String msg = "<br/>" + failureNum + "????????? " + user.getUsername() + " ???????????????";
				failureMsg.append(msg + e.getMessage());
			}
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "?????????????????????????????? " + failureNum + " ??????????????????????????????????????????");
			throw new HtException(failureMsg.toString());
		} else {
			successMsg.insert(0, "????????????????????????????????????????????? " + successNum + " ?????????????????????");
		}
		return successMsg.toString();
	}

}
