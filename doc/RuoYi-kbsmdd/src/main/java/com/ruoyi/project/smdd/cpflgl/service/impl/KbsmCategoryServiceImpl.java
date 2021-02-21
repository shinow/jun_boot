package com.ruoyi.project.smdd.cpflgl.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.cpflgl.mapper.KbsmCategoryMapper;
import com.ruoyi.project.smdd.cpflgl.domain.KbsmCategory;
import com.ruoyi.project.smdd.cpflgl.service.IKbsmCategoryService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 菜品分类管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
public class KbsmCategoryServiceImpl implements IKbsmCategoryService 
{
    @Autowired
    private KbsmCategoryMapper kbsmCategoryMapper;

    /**
     * 查询菜品分类管理
     * 
     * @param id 菜品分类管理ID
     * @return 菜品分类管理
     */
    @Override
    public KbsmCategory selectKbsmCategoryById(Long id)
    {
        return kbsmCategoryMapper.selectKbsmCategoryById(id);
    }

    /**
     * 查询菜品分类管理列表
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 菜品分类管理
     */
    @Override
    public List<KbsmCategory> selectKbsmCategoryList(KbsmCategory kbsmCategory)
    {
        return kbsmCategoryMapper.selectKbsmCategoryList(kbsmCategory);
    }

    /**
     * 新增菜品分类管理
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 结果
     */
    @Override
    public int insertKbsmCategory(KbsmCategory kbsmCategory)
    {
        return kbsmCategoryMapper.insertKbsmCategory(kbsmCategory);
    }

    /**
     * 修改菜品分类管理
     * 
     * @param kbsmCategory 菜品分类管理
     * @return 结果
     */
    @Override
    public int updateKbsmCategory(KbsmCategory kbsmCategory)
    {
        return kbsmCategoryMapper.updateKbsmCategory(kbsmCategory);
    }

    /**
     * 删除菜品分类管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmCategoryByIds(String ids)
    {
        return kbsmCategoryMapper.deleteKbsmCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除菜品分类管理信息
     * 
     * @param id 菜品分类管理ID
     * @return 结果
     */
    @Override
    public int deleteKbsmCategoryById(Long id)
    {
        return kbsmCategoryMapper.deleteKbsmCategoryById(id);
    }
}
