package com.usthe.bootshiro.service;

import com.usthe.bootshiro.domain.bo.AuthUser;
import com.usthe.bootshiro.domain.vo.Account;

/* *
 * @author Wujun
 * @Description 
 * @Date 22:02 2018/3/7
 */
public interface AccountService {

    Account loadAccount(String appId);
    boolean isAccountExistByUid(String uid);
    boolean registerAccount(AuthUser account);
    String loadAccountRole(String appId);
}
