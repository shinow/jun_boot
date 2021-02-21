package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.wxapi.model.bean.GoodsImage;
import com.ruoyi.project.wxapi.model.mapper.GoodsImageMapper;
import com.ruoyi.project.wxapi.model.service.IGoodsImageService;
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
public class GoodsImageServiceImpl extends ServiceImpl<GoodsImageMapper, GoodsImage> implements IGoodsImageService {

    @Override
    public GoodsImage getByGoodsId(Integer goodsId) {
        return this.getBaseMapper().getByGoodsId(goodsId);
    }

    @Override
    public List<GoodsImage> selectByGoodsId(Integer goodsId) {
        return this.getBaseMapper().selectByGoodsId(goodsId);
    }
}
