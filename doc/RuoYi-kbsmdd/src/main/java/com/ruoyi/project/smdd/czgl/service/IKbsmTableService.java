package com.ruoyi.project.smdd.czgl.service;

import java.util.List;
import com.ruoyi.project.smdd.czgl.domain.KbsmTable;

/**
 * 餐桌管理Service接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface IKbsmTableService 
{
    /**
     * 查询餐桌管理
     * 
     * @param id 餐桌管理ID
     * @return 餐桌管理
     */
    public KbsmTable selectKbsmTableById(Long id);

    /**
     * 查询餐桌管理列表
     * 
     * @param kbsmTable 餐桌管理
     * @return 餐桌管理集合
     */
    public List<KbsmTable> selectKbsmTableList(KbsmTable kbsmTable);

    /**
     * 新增餐桌管理
     * 
     * @param kbsmTable 餐桌管理
     * @return 结果
     */
    public int insertKbsmTable(KbsmTable kbsmTable);

    /**
     * 修改餐桌管理
     * 
     * @param kbsmTable 餐桌管理
     * @return 结果
     */
    public int updateKbsmTable(KbsmTable kbsmTable);

    /**
     * 批量删除餐桌管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmTableByIds(String ids);

    /**
     * 删除餐桌管理信息
     * 
     * @param id 餐桌管理ID
     * @return 结果
     */
    public int deleteKbsmTableById(Long id);
}
