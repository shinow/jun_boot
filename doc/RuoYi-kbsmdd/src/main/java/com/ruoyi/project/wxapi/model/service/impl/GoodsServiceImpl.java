package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.wxapi.model.bean.Goods;
import com.ruoyi.project.wxapi.model.mapper.GoodsMapper;
import com.ruoyi.project.wxapi.model.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-10
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public List<Goods> listByCategoryId(Integer categoryId) {
        return this.getBaseMapper().listByCategoryId(categoryId);
    }

    @Override
    public Goods getByGoodsId(Integer goodsId) {
        return this.getBaseMapper().getByGoodsId(goodsId);
    }
}
