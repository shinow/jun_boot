package com.ruoyi.cms.mapper;

import com.ruoyi.cms.domain.UserSignIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户签到Mapper接口
 * 
 * @author markbro
 * @date 2020-02-03
 */
public interface UserSignInMapper 
{
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
     * 删除用户签到
     * 
     * @param id 用户签到ID
     * @return 结果
     */
    public int deleteUserSignInById(Long id);

    /**
     * 批量删除用户签到
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserSignInByIds(String[] ids);


    //获得用户某天的签到信息
    public UserSignIn getByDate(@Param("userId")String userId, @Param("dateStr")String dateStr);
    //获取用户某月份的签到记录
    public List<UserSignIn> findByMonth(@Param("userId")String userId,@Param("month")String month);
    //获得用户最近一次签到记录
    public UserSignIn getLast(@Param("userId")String userId);

}
