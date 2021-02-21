package com.ruoyi.project.smdd.cpgl.service.impl;

import java.util.List;

import com.ruoyi.project.wxapi.model.mapper.GoodsImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.cpgl.mapper.KbsmGoodsMapper;
import com.ruoyi.project.smdd.cpgl.domain.KbsmGoods;
import com.ruoyi.project.smdd.cpgl.service.IKbsmGoodsService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 菜品管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-09
 */
@Service
public class KbsmGoodsServiceImpl implements IKbsmGoodsService 
{
    @Autowired
    private KbsmGoodsMapper kbsmGoodsMapper;
    @Autowired
    private GoodsImageMapper goodsImageMapper;

    /**
     * 查询菜品管理
     * 
     * @param id 菜品管理ID
     * @return 菜品管理
     */
    @Override
    public KbsmGoods selectKbsmGoodsById(Long id)
    {
        return kbsmGoodsMapper.selectKbsmGoodsById(id);
    }

    /**
     * 查询菜品管理列表
     * 
     * @param kbsmGoods 菜品管理
     * @return 菜品管理
     */
    @Override
    public List<KbsmGoods> selectKbsmGoodsList(KbsmGoods kbsmGoods)
    {
        return kbsmGoodsMapper.selectKbsmGoodsList(kbsmGoods);
    }

    /**
     * 新增菜品管理
     * 
     * @param kbsmGoods 菜品管理
     * @return 结果
     */
    @Override
    public int insertKbsmGoods(KbsmGoods kbsmGoods, Integer imgId)
    {
        Integer flag = kbsmGoodsMapper.insertKbsmGoods(kbsmGoods);
        KbsmGoods getKbsmGoods = kbsmGoodsMapper.getByGoodsNameAndWxappId(kbsmGoods.getGoodsName(), kbsmGoods.getWxappId());
        Integer flag2 = goodsImageMapper.insertOnDublicateKey(getKbsmGoods.getId(), imgId, getKbsmGoods.getWxappId());
        if (flag > 0 && flag2 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 修改菜品管理
     * 
     * @param kbsmGoods 菜品管理
     * @return 结果
     */
    @Override
    public int updateKbsmGoods(KbsmGoods kbsmGoods)
    {
        return kbsmGoodsMapper.updateKbsmGoods(kbsmGoods);
    }

    /**
     * 删除菜品管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsByIds(String ids)
    {
        return kbsmGoodsMapper.deleteKbsmGoodsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除菜品管理信息
     * 
     * @param id 菜品管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmGoodsById(Long id)
    {
        return kbsmGoodsMapper.deleteKbsmGoodsById(id);
    }
}
