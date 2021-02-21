package com.ruoyi.project.smdd.yhgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.yhgl.mapper.KbsmUserMapper;
import com.ruoyi.project.smdd.yhgl.domain.KbsmUser;
import com.ruoyi.project.smdd.yhgl.service.IKbsmUserService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmUserServiceImpl implements IKbsmUserService 
{
    @Autowired
    private KbsmUserMapper kbsmUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理ID
     * @return 用户管理
     */
    @Override
    public KbsmUser selectKbsmUserById(Long id)
    {
        return kbsmUserMapper.selectKbsmUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param kbsmUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<KbsmUser> selectKbsmUserList(KbsmUser kbsmUser)
    {
        return kbsmUserMapper.selectKbsmUserList(kbsmUser);
    }

    /**
     * 新增用户管理
     * 
     * @param kbsmUser 用户管理
     * @return 结果
     */
    @Override
    public int insertKbsmUser(KbsmUser kbsmUser)
    {
        return kbsmUserMapper.insertKbsmUser(kbsmUser);
    }

    /**
     * 修改用户管理
     * 
     * @param kbsmUser 用户管理
     * @return 结果
     */
    @Override
    public int updateKbsmUser(KbsmUser kbsmUser)
    {
        return kbsmUserMapper.updateKbsmUser(kbsmUser);
    }

    /**
     * 删除用户管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmUserByIds(String ids)
    {
        return kbsmUserMapper.deleteKbsmUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmUserById(Long id)
    {
        return kbsmUserMapper.deleteKbsmUserById(id);
    }
}
