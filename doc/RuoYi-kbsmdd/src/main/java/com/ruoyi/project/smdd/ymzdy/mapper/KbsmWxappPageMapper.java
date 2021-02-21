package com.ruoyi.project.smdd.ymzdy.mapper;

import java.util.List;
import com.ruoyi.project.smdd.ymzdy.domain.KbsmWxappPage;

/**
 * 页面自定义Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
public interface KbsmWxappPageMapper 
{
    /**
     * 查询页面自定义
     * 
     * @param id 页面自定义ID
     * @return 页面自定义
     */
    public KbsmWxappPage selectKbsmWxappPageById(Long id);

    /**
     * 查询页面自定义列表
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 页面自定义集合
     */
    public List<KbsmWxappPage> selectKbsmWxappPageList(KbsmWxappPage kbsmWxappPage);

    /**
     * 新增页面自定义
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 结果
     */
    public int insertKbsmWxappPage(KbsmWxappPage kbsmWxappPage);

    /**
     * 修改页面自定义
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 结果
     */
    public int updateKbsmWxappPage(KbsmWxappPage kbsmWxappPage);

    /**
     * 删除页面自定义
     * 
     * @param id 页面自定义ID
     * @return 结果
     */
    public int deleteKbsmWxappPageById(Long id);

    /**
     * 批量删除页面自定义
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmWxappPageByIds(String[] ids);

    KbsmWxappPage selectKbsmWxappPageByWxappId(Long wxappId);

    KbsmWxappPage selectKbsmWxappTypeId(int typeId);

    int updateKbsmWxappPageByWxappId(KbsmWxappPage kbsmWxappPage);
}
