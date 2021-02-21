package com.ruoyi.project.smdd.mdgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.mdgl.mapper.KbsmShopMapper;
import com.ruoyi.project.smdd.mdgl.domain.KbsmShop;
import com.ruoyi.project.smdd.mdgl.service.IKbsmShopService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 门店管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmShopServiceImpl implements IKbsmShopService 
{
    @Autowired
    private KbsmShopMapper kbsmShopMapper;

    /**
     * 查询门店管理
     * 
     * @param id 门店管理ID
     * @return 门店管理
     */
    @Override
    public KbsmShop selectKbsmShopById(Long id)
    {
        return kbsmShopMapper.selectKbsmShopById(id);
    }

    /**
     * 查询门店管理列表
     * 
     * @param kbsmShop 门店管理
     * @return 门店管理
     */
    @Override
    public List<KbsmShop> selectKbsmShopList(KbsmShop kbsmShop)
    {
        return kbsmShopMapper.selectKbsmShopList(kbsmShop);
    }

    /**
     * 新增门店管理
     * 
     * @param kbsmShop 门店管理
     * @return 结果
     */
    @Override
    public int insertKbsmShop(KbsmShop kbsmShop)
    {
        return kbsmShopMapper.insertKbsmShop(kbsmShop);
    }

    /**
     * 修改门店管理
     * 
     * @param kbsmShop 门店管理
     * @return 结果
     */
    @Override
    public int updateKbsmShop(KbsmShop kbsmShop)
    {
        return kbsmShopMapper.updateKbsmShop(kbsmShop);
    }

    /**
     * 删除门店管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmShopByIds(String ids)
    {
        return kbsmShopMapper.deleteKbsmShopByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除门店管理信息
     * 
     * @param id 门店管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmShopById(Long id)
    {
        return kbsmShopMapper.deleteKbsmShopById(id);
    }
}
