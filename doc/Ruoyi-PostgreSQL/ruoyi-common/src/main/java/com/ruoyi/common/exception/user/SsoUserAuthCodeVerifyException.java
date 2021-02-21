package com.ruoyi.common.exception.user;

/**
 * @ClassName: com.ruoyi.common.exception.user
 * @Description: ******  验证码验证异常
 * @author: andy-zhangbingbing
 * @version: V1.0
 * @Date: 2020/7/17 10:54
 **/
public class SsoUserAuthCodeVerifyException extends UserException{

    public SsoUserAuthCodeVerifyException(){
        super("sso.user.authcode.verify.fail",null);
    }
}
