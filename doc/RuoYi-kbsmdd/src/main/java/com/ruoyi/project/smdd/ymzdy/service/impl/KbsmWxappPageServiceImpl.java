package com.ruoyi.project.smdd.ymzdy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.smdd.ymzdy.mapper.KbsmWxappPageMapper;
import com.ruoyi.project.smdd.ymzdy.domain.KbsmWxappPage;
import com.ruoyi.project.smdd.ymzdy.service.IKbsmWxappPageService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 页面自定义Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-05
 */
@Service
public class KbsmWxappPageServiceImpl implements IKbsmWxappPageService 
{
    @Autowired
    private KbsmWxappPageMapper kbsmWxappPageMapper;

    /**
     * 查询页面自定义
     * 
     * @param id 页面自定义ID
     * @return 页面自定义
     */
    @Override
    public KbsmWxappPage selectKbsmWxappPageById(Long id)
    {
        return kbsmWxappPageMapper.selectKbsmWxappPageById(id);
    }

    @Override
    public KbsmWxappPage selectKbsmWxappPageByWxappId(Long wxappId) {
        return kbsmWxappPageMapper.selectKbsmWxappPageByWxappId(wxappId);
    }

    /**
     * 查询页面自定义列表
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 页面自定义
     */
    @Override
    public List<KbsmWxappPage> selectKbsmWxappPageList(KbsmWxappPage kbsmWxappPage)
    {
        return kbsmWxappPageMapper.selectKbsmWxappPageList(kbsmWxappPage);
    }

    /**
     * 新增页面自定义
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 结果
     */
    @Override
    public int insertKbsmWxappPage(KbsmWxappPage kbsmWxappPage)
    {
        return kbsmWxappPageMapper.insertKbsmWxappPage(kbsmWxappPage);
    }

    /**
     * 修改页面自定义
     * 
     * @param kbsmWxappPage 页面自定义
     * @return 结果
     */
    @Override
    public int updateKbsmWxappPage(KbsmWxappPage kbsmWxappPage)
    {
        return kbsmWxappPageMapper.updateKbsmWxappPage(kbsmWxappPage);
    }

    /**
     * 删除页面自定义对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKbsmWxappPageByIds(String ids)
    {
        return kbsmWxappPageMapper.deleteKbsmWxappPageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除页面自定义信息
     * 
     * @param id 页面自定义ID
     * @return 结果
     */
    @Override
    public int deleteKbsmWxappPageById(Long id)
    {
        return kbsmWxappPageMapper.deleteKbsmWxappPageById(id);
    }

    @Override
    public KbsmWxappPage selectKbsmWxappTypeId(int typeId) {
        return kbsmWxappPageMapper.selectKbsmWxappTypeId(typeId);
    }

    @Override
    public int updateKbsmWxappPageByWxappId(KbsmWxappPage kbsmWxappPage) {
        return kbsmWxappPageMapper.updateKbsmWxappPageByWxappId(kbsmWxappPage);
    }
}
