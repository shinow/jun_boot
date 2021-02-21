package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OkrAttach;

/**
 * OKR附加属性表Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-19
 */
public interface IOkrAttachService 
{
    /**
     * 查询OKR附加属性表
     * 
     * @param id OKR附加属性表ID
     * @return OKR附加属性表
     */
    public OkrAttach selectOkrAttachById(Long id);

    /**
     * 查询OKR附加属性表列表
     * 
     * @param okrAttach OKR附加属性表
     * @return OKR附加属性表集合
     */
    public List<OkrAttach> selectOkrAttachList(OkrAttach okrAttach);

    /**
     * 新增OKR附加属性表
     * 
     * @param okrAttach OKR附加属性表
     * @return 结果
     */
    public int insertOkrAttach(OkrAttach okrAttach);


    /**
     * 批量新增OKR附加属性表
     *
     * @param okrAttaches OKR附加属性表
     * @return 结果
     */
    public int insertOkrAttachList(List<OkrAttach> okrAttaches);

    /**
     * 修改OKR附加属性表
     * 
     * @param okrAttach OKR附加属性表
     * @return 结果
     */
    public int updateOkrAttach(OkrAttach okrAttach);

    /**
     * 批量删除OKR附加属性表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrAttachByIds(String ids);

    /**
     * 删除OKR附加属性表信息
     * 
     * @param id OKR附加属性表ID
     * @return 结果
     */
    public int deleteOkrAttachById(Long id);
}
