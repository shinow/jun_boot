package com.ruoyi.project.smdd.dygl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.dygl.mapper.KbsmShopClerkMapper;
import com.ruoyi.project.smdd.dygl.domain.KbsmShopClerk;
import com.ruoyi.project.smdd.dygl.service.IKbsmShopClerkService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 店员管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmShopClerkServiceImpl implements IKbsmShopClerkService 
{
    @Autowired
    private KbsmShopClerkMapper kbsmShopClerkMapper;

    /**
     * 查询店员管理
     * 
     * @param id 店员管理ID
     * @return 店员管理
     */
    @Override
    public KbsmShopClerk selectKbsmShopClerkById(Long id)
    {
        return kbsmShopClerkMapper.selectKbsmShopClerkById(id);
    }

    /**
     * 查询店员管理列表
     * 
     * @param kbsmShopClerk 店员管理
     * @return 店员管理
     */
    @Override
    public List<KbsmShopClerk> selectKbsmShopClerkList(KbsmShopClerk kbsmShopClerk)
    {
        return kbsmShopClerkMapper.selectKbsmShopClerkList(kbsmShopClerk);
    }

    /**
     * 新增店员管理
     * 
     * @param kbsmShopClerk 店员管理
     * @return 结果
     */
    @Override
    public int insertKbsmShopClerk(KbsmShopClerk kbsmShopClerk)
    {
        return kbsmShopClerkMapper.insertKbsmShopClerk(kbsmShopClerk);
    }

    /**
     * 修改店员管理
     * 
     * @param kbsmShopClerk 店员管理
     * @return 结果
     */
    @Override
    public int updateKbsmShopClerk(KbsmShopClerk kbsmShopClerk)
    {
        return kbsmShopClerkMapper.updateKbsmShopClerk(kbsmShopClerk);
    }

    /**
     * 删除店员管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmShopClerkByIds(String ids)
    {
        return kbsmShopClerkMapper.deleteKbsmShopClerkByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店员管理信息
     * 
     * @param id 店员管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmShopClerkById(Long id)
    {
        return kbsmShopClerkMapper.deleteKbsmShopClerkById(id);
    }
}
