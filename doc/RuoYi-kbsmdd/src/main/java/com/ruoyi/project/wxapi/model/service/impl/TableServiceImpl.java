package com.ruoyi.project.wxapi.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.project.wxapi.model.bean.Table;
import com.ruoyi.project.wxapi.model.mapper.TableMapper;
import com.ruoyi.project.wxapi.model.service.ITableService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hnkb
 * @since 2020-07-20
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    @Override
    public Table getByTableId(Integer tableId) {
        return this.getBaseMapper().getByTableId(tableId);
    }
}
