package com.ruoyi.framework.shiro.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.user.SsoUserNotExistsException;
import com.ruoyi.common.exception.user.SsoUserNotNullException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: com.ruoyi.framework.shiro.service
 * @Description: ******
 * @author: andy-zhangbingbing
 * @version: V1.0
 * @Date: 2020/7/17 10:46
 **/
@Component
public class SsoUserLoginService {
    private static Logger logger = LoggerFactory.getLogger(SsoUserLoginService.class);

    @Autowired
    private ISysUserService userService;

    /**
     * 单点登录功能的实现，传送了用户名和机构号，授权码。可以去认证中心进行验证。
     * 这里省去了验证的过程。
     * @param username  用户名
     * @param branchId  机构号
     * @param authCode  授权码
     * @return
     * @throws Exception
     */
    public SysUser login(String username,String branchId,String authCode) throws Exception{
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(branchId)||StringUtils.isEmpty(authCode)){
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("sso.not.null")));
            throw new SsoUserNotNullException();
        }
        logger.info("username=="+ username+"==branchId=="+branchId+"==authcode=="+authCode);
        try{
            //check the authode

        } catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            throw new Exception(e);
        }
        //查询用户信息，如果是不同的用户表，就可以通过这里进行配置，然后进行返回。
        SysUser user = userService.selectUserByLoginName(username);
        //判断用户信息是否查到，如果没有则没该用户
        if(user ==null){
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,Constants.LOGIN_FAIL,MessageUtils.message("sso.user.not.exists")));
            throw new SsoUserNotExistsException();
        }

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,Constants.LOGIN_SUCCESS,MessageUtils.message("sso.user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 记录登录信息
     * @param user
     */
    public void recordLoginInfo(SysUser user){
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }
}
