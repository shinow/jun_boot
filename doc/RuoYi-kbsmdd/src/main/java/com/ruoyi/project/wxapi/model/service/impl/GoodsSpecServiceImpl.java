package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.GoodsSpec;
import com.ruoyi.project.wxapi.model.bean.GoodsSpecGroup;
import com.ruoyi.project.wxapi.model.mapper.GoodsSpecGroupMapper;
import com.ruoyi.project.wxapi.model.mapper.GoodsSpecMapper;
import com.ruoyi.project.wxapi.model.service.IGoodsSpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
@Service
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec> implements IGoodsSpecService {
    @Autowired
    GoodsSpecGroupMapper goodsSpecGroupMapper;

    @Override
    public Map<GoodsSpecGroup, List<GoodsSpec>> getMUltiByGoodsId(Integer goodsId) {
        Map<GoodsSpecGroup, List<GoodsSpec>> dataMap = new HashMap<>();
        List<GoodsSpecGroup> goodsSpecGroups = goodsSpecGroupMapper.selectByGoodsId(goodsId);
        for (int i = 0; i < goodsSpecGroups.size(); i++) {
            GoodsSpecGroup goodsSpecGroup = goodsSpecGroups.get(i);
            List<GoodsSpec> goodsSpecs = this.getBaseMapper().selectByGroupId(goodsSpecGroup.getId());
            dataMap.put(goodsSpecGroup, goodsSpecs);
        }

        return dataMap;
    }

    @Override
    public GoodsSpec getSingleByGoodsId(Integer goodsId) {
        return this.getBaseMapper().getByGoodsId(goodsId);
    }

    @Override
    public GoodsSpec getNotGroupByGoodsId(Integer goodsId) {
        return this.getBaseMapper().getNotGroupByGoodsId(goodsId);
    }
}
