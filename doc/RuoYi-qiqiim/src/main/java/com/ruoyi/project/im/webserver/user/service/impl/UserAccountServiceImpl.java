package com.ruoyi.project.im.webserver.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.im.webserver.user.mapper.UserAccountMapper;
import com.ruoyi.project.im.webserver.user.model.UserAccountEntity;
import com.ruoyi.project.im.webserver.user.model.UserInfoEntity;
import com.ruoyi.project.im.webserver.user.service.UserAccountService;
import com.ruoyi.project.im.webserver.user.service.UserInfoService;



@Service("userAccountServiceImpl")
public class UserAccountServiceImpl implements UserAccountService {
	@Autowired
	private UserAccountMapper userAccountDao;
	@Autowired
	private UserInfoService userInfoServiceImpl;
	
	@Override
	public UserAccountEntity queryObject(Long id){
		return userAccountDao.queryObject(id);
	}
	
	@Override
	public List<UserAccountEntity> queryList(Map<String, Object> map){
		return userAccountDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userAccountDao.queryTotal(map);
	}
	
	@Override
	public void save(UserAccountEntity userAccount){
		//判断用户是否已注册
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("account", userAccount.getAccount());
		UserAccountEntity  user = queryObjectByAccount(map);
		if(user==null||user.getId()<1){
			userAccountDao.save(userAccount);
			if(userAccount!=null&&userAccount.getId()!=null){
				//保存基本信息
				UserInfoEntity userInfo = userAccount.getUserInfo();
				userInfo.setUid(userAccount.getId());
				userInfoServiceImpl.save(userInfo);
			}
		} 
	}
	
	@Override
	public int update(UserAccountEntity userAccount){
		return userAccountDao.update(userAccount);
	}
	
	@Override
	public int delete(Long id){
		return userAccountDao.delete(id);
	}
	
	@Override
	public int deleteBatch(Long[] ids){
		return userAccountDao.deleteBatch(ids);
	}

	@Override
	public UserAccountEntity queryObjectByAccount(Map<String, Object> map) {
		return userAccountDao.queryObjectByAccount(map);
	}

	@Override
	public UserAccountEntity validateUser(Map<String, Object> map) {
		UserAccountEntity  user = queryObjectByAccount(map);
		if(user!=null){
			String password = (String)map.get("password");
			if(password.equals(user.getPassword())){
				return user;
			}
		}
		return null;
	}
	
}
