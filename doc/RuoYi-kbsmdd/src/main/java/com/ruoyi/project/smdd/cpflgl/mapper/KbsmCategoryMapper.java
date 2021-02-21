package com.ruoyi.project.smdd.cpflgl.mapper;

import java.util.List;
import com.ruoyi.project.smdd.cpflgl.domain.KbsmCategory;

/**
 * 菜品分类管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface KbsmCategoryMapper 
{
    /**
     * 查询菜品分类管理
     * 
     * @param id 菜品分类管理ID
     * @return 菜品分类管理
     */
    public KbsmCategory selectKbsmCategoryById(Long id);

    /**
     * 查询菜品分类管理列表
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 菜品分类管理集合
     */
    public List<KbsmCategory> selectKbsmCategoryList(KbsmCategory kbsmCategory);

    /**
     * 新增菜品分类管理
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 结果
     */
    public int insertKbsmCategory(KbsmCategory kbsmCategory);

    /**
     * 修改菜品分类管理
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 结果
     */
    public int updateKbsmCategory(KbsmCategory kbsmCategory);

    /**
     * 删除菜品分类管理
     * 
     * @param id 菜品分类管理ID
     * @return 结果
     */
    public int deleteKbsmCategoryById(Long id);

    /**
     * 批量删除菜品分类管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmCategoryByIds(String[] ids);
}
