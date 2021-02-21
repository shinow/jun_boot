package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.Category;
import com.ruoyi.project.wxapi.model.mapper.CategoryMapper;
import com.ruoyi.project.wxapi.model.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<Category> listByShopId(Integer shop_id) {
        return this.getBaseMapper().listByShopId(shop_id);
    }
}
