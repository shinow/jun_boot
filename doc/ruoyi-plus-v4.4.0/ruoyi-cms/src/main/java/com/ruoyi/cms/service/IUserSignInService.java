package com.ruoyi.cms.service;

import com.ruoyi.cms.domain.UserSignIn;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * 用户签到Service接口
 * 
 * @author markbro
 * @date 2020-02-03
 */
public interface IUserSignInService {
    /**
     * 查询用户签到
     *
     * @param id 用户签到ID
     * @return 用户签到
     */
    public UserSignIn selectUserSignInById(Long id);

    /**
     * 查询用户签到列表
     *
     * @param userSignIn 用户签到
     * @return 用户签到集合
     */
    public List<UserSignIn> selectUserSignInList(UserSignIn userSignIn);

    /**
     * 新增用户签到
     *
     * @param userSignIn 用户签到
     * @return 结果
     */
    public int insertUserSignIn(UserSignIn userSignIn);

    /**
     * 修改用户签到
     *
     * @param userSignIn 用户签到
     * @return 结果
     */
    public int updateUserSignIn(UserSignIn userSignIn);

    /**
     * 批量删除用户签到
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserSignInByIds(String ids);

    /**
     * 删除用户签到信息
     *
     * @param id 用户签到ID
     * @return 结果
     */
    public int deleteUserSignInById(Long id);


    public AjaxResult initSignPage(String userId);

    public AjaxResult signIn(String userId);
}
