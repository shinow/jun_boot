package com.ruoyi.project.smdd.yhgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.yhgl.domain.KbsmUser;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface KbsmUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理ID
     * @return 用户管理
     */
    public KbsmUser selectKbsmUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param kbsmUser 用户管理
     * @return 用户管理集合
     */
    public List<KbsmUser> selectKbsmUserList(KbsmUser kbsmUser);

    /**
     * 新增用户管理
     * 
     * @param kbsmUser 用户管理
     * @return 结果
     */
    public int insertKbsmUser(KbsmUser kbsmUser);

    /**
     * 修改用户管理
     * 
     * @param kbsmUser 用户管理
     * @return 结果
     */
    public int updateKbsmUser(KbsmUser kbsmUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理ID
     * @return 结果
     */
    public int deleteKbsmUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmUserByIds(String[] ids);
}
