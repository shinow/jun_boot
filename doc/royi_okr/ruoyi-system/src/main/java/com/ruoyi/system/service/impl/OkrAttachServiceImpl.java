package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrAttachMapper;
import com.ruoyi.system.domain.OkrAttach;
import com.ruoyi.system.service.IOkrAttachService;
import com.ruoyi.common.core.text.Convert;

/**
 * OKR附加属性表Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-19
 */
@Service
public class OkrAttachServiceImpl implements IOkrAttachService 
{
    @Autowired
    private OkrAttachMapper okrAttachMapper;

    /**
     * 查询OKR附加属性表
     * 
     * @param id OKR附加属性表ID
     * @return OKR附加属性表
     */
    @Override
    public OkrAttach selectOkrAttachById(Long id)
    {
        return okrAttachMapper.selectOkrAttachById(id);
    }

    /**
     * 查询OKR附加属性表列表
     * 
     * @param okrAttach OKR附加属性表
     * @return OKR附加属性表
     */
    @Override
    public List<OkrAttach> selectOkrAttachList(OkrAttach okrAttach)
    {
        return okrAttachMapper.selectOkrAttachList(okrAttach);
    }

    /**
     * 新增OKR附加属性表
     * 
     * @param okrAttach OKR附加属性表
     * @return 结果
     */
    @Override
    public int insertOkrAttach(OkrAttach okrAttach)
    {
        return okrAttachMapper.insertOkrAttach(okrAttach);
    }

    @Override
    public int insertOkrAttachList(List<OkrAttach> okrAttaches) {
        return okrAttachMapper.insertOkrAttachList(okrAttaches);
    }

    /**
     * 修改OKR附加属性表
     * 
     * @param okrAttach OKR附加属性表
     * @return 结果
     */
    @Override
    public int updateOkrAttach(OkrAttach okrAttach)
    {
        return okrAttachMapper.updateOkrAttach(okrAttach);
    }

    /**
     * 删除OKR附加属性表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrAttachByIds(String ids)
    {
        return okrAttachMapper.deleteOkrAttachByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除OKR附加属性表信息
     * 
     * @param id OKR附加属性表ID
     * @return 结果
     */
    @Override
    public int deleteOkrAttachById(Long id)
    {
        return okrAttachMapper.deleteOkrAttachById(id);
    }
}
