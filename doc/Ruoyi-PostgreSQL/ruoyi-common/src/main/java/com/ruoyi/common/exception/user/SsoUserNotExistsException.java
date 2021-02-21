package com.ruoyi.common.exception.user;

/**
 * @ClassName: com.ruoyi.common.exception.user
 * @Description: ******
 * @author: andy-zhangbingbing
 * @version: V1.0
 * @Date: 2020/7/17 10:57
 **/
public class SsoUserNotExistsException extends UserException {
    public SsoUserNotExistsException() {
        super("sso.user.not.exists",null);
    }
}
