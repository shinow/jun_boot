package com.ruoyi.project.smdd.czgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.czgl.mapper.KbsmTableMapper;
import com.ruoyi.project.smdd.czgl.domain.KbsmTable;
import com.ruoyi.project.smdd.czgl.service.IKbsmTableService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 餐桌管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmTableServiceImpl implements IKbsmTableService 
{
    @Autowired
    private KbsmTableMapper kbsmTableMapper;

    /**
     * 查询餐桌管理
     * 
     * @param id 餐桌管理ID
     * @return 餐桌管理
     */
    @Override
    public KbsmTable selectKbsmTableById(Long id)
    {
        return kbsmTableMapper.selectKbsmTableById(id);
    }

    /**
     * 查询餐桌管理列表
     * 
     * @param kbsmTable 餐桌管理
     * @return 餐桌管理
     */
    @Override
    public List<KbsmTable> selectKbsmTableList(KbsmTable kbsmTable)
    {
        return kbsmTableMapper.selectKbsmTableList(kbsmTable);
    }

    /**
     * 新增餐桌管理
     * 
     * @param kbsmTable 餐桌管理
     * @return 结果
     */
    @Override
    public int insertKbsmTable(KbsmTable kbsmTable)
    {
        return kbsmTableMapper.insertKbsmTable(kbsmTable);
    }

    /**
     * 修改餐桌管理
     * 
     * @param kbsmTable 餐桌管理
     * @return 结果
     */
    @Override
    public int updateKbsmTable(KbsmTable kbsmTable)
    {
        return kbsmTableMapper.updateKbsmTable(kbsmTable);
    }

    /**
     * 删除餐桌管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmTableByIds(String ids)
    {
        return kbsmTableMapper.deleteKbsmTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除餐桌管理信息
     * 
     * @param id 餐桌管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmTableById(Long id)
    {
        return kbsmTableMapper.deleteKbsmTableById(id);
    }
}
