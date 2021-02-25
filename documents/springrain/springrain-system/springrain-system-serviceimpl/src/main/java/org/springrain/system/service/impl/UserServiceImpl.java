package org.springrain.system.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.*;
import org.springrain.frame.util.CommonEnum.ACTIVE;
import org.springrain.rpc.sessionuser.UserVO;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserRoleOrgService;
import org.springrain.system.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-07-06 16:03:00
 */
@Service("userService")
public class UserServiceImpl extends BaseSpringrainServiceImpl implements IUserService {

	@Resource
    private IUserRoleOrgService IUserRoleOrgService;


	@Override
    public String save(IBaseEntity entity) throws Exception {
        return super.save(entity).toString();
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        User user = (User) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findUserById_" + user.getId());
        return super.update(entity);
    }

    @Override
    public Integer update(IBaseEntity entity, boolean onlyupdatenotnull) throws Exception {
        User user = (User) entity;
        //清理缓存
        super.evictByKey(GlobalStatic.userOrgRoleMenuInfoCacheKey, "findUserById_" + user.getId());
        return super.update(entity, onlyupdatenotnull);
    }


    @Override
    public User findUserById(String id) throws Exception {

        if (StringUtils.isBlank(id)) {
            return null;
        }
        String key = "findUserById_" + id;
        User user = super.getByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, User.class);
        if (user != null) {
            return user;
        }
        user = super.findById(id, User.class);
        if (user == null) {
            return null;
        }
        // 加上缓存
        super.putByCache(GlobalStatic.userOrgRoleMenuInfoCacheKey, key, user);
        return user;
    }


    @Override
    public UserVO findUserVOByUserId(String userId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return null;
        }
        User user = findUserById(userId);
        if (user == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setAccount(user.getAccount());
        userVO.setEmail(user.getEmail());
        userVO.setUserName(user.getUserName());
        userVO.setUserType(user.getUserType());
        userVO.setActive(user.getActive());


        return userVO;
    }

    @Override
    public String findUserIdByOpenId(String openId) throws Exception {

        if (StringUtils.isBlank(openId)) {
            return null;
        }
        Finder finder = Finder.getSelectFinder(User.class, " id ").append(" WHERE openId=:openId ").setParam("openId", openId);
        return super.queryForObject(finder, String.class);
    }

    @Override
    public String wrapJwtTokenByUser(User user) throws Exception {
        Map<String, Object> jwtSignMap = new HashMap<>();
        jwtSignMap.put("userId", user.getId());
        jwtSignMap.put("account", user.getAccount());
        jwtSignMap.put("userName", user.getUserName());
        jwtSignMap.put("userType", user.getUserType());

        String jwtToken = JwtUtils.sign(jwtSignMap);
        // RSA 私钥加密
        jwtToken = SecUtils.encoderByRSAPrivateKey(jwtToken);
        return jwtToken;
    }


    @Override
    public User findLoginUser(String account, String password, Integer userType) throws Exception {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return null;
        }
        // Finder finder = new Finder("SELECT * FROM t_user WHERE account=:account ");
        Finder finder = Finder.getSelectFinder(User.class).append(" WHERE  account=:account  and password=:password  and active=1 ");
        finder.setParam("account", account).setParam("password", password);

        return super.queryForObject(finder, User.class);
    }


	@Override
	public List<User> findUserList(Page<User> page) throws Exception {
		Finder finder = Finder.getSelectFinder(User.class)
				.append(" WHERE active=:active");
		finder.setParam("active", ACTIVE.未删除.getState());
		
		// 处理查询条件
		User queryBean = page.getData();
		if(queryBean != null) {
			if(StringUtils.isNotBlank(queryBean.getDeptId())) {
				// 按部门查询
				List<String> userIdList = IUserRoleOrgService.findUserIdListByOrgId(queryBean.getDeptId());
				if(CollectionUtils.isEmpty(userIdList)) {
					return null;
				}
				finder.append(" AND id in (:userIdList)").setParam("userIdList", userIdList);
			}
			
			if(StringUtils.isNotBlank(queryBean.getUserName())) {
				// 按姓名查询
				finder.append(" AND userName like :userName")
					.setParam("userName", "%" + queryBean.getUserName() + "%");
			}
			
			if(StringUtils.isNotBlank(queryBean.getMobile())) {
				// 按手机号查询
				finder.append(" AND mobile like :mobile")
					.setParam("mobile", "%" + queryBean.getMobile() + "%");
			}
			
			if(queryBean.getStatus() != null) {
				// 按状态查询
				finder.append(" AND status=:status")
					.setParam("status", "%" + queryBean.getStatus() + "%");
			}

			if(page.getBeginTime()!=null && page.getEndTime()!=null) {
                finder.append(" AND createTime>=:beginTime AND createTime<=:endTime ")
                        .setParam("beginTime", page.getBeginTime())
                        .setParam("endTime", DateUtils.addDays(page.getEndTime(),1));
            }
		}
		
		return this.queryForList(finder, User.class, page);
	}


}
