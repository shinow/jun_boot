package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OkrThumbsUp;

/**
 * 点赞Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
public interface IOkrThumbsUpService 
{
    /**
     * 查询点赞 
     * 
     * @param id 点赞 ID
     * @return 点赞 
     */
    public OkrThumbsUp selectOkrThumbsUpById(Long id);

    /**
     * 查询点赞 列表
     * 
     * @param okrThumbsUp 点赞 
     * @return 点赞 集合
     */
    public List<OkrThumbsUp> selectOkrThumbsUpList(OkrThumbsUp okrThumbsUp);



    /**
     * 查询点赞 列表
     *
     * @param okrThumbsUp 点赞 
     * @return 点赞 集合
     */
    public OkrThumbsUp selectOkrThumbsUpOne(OkrThumbsUp okrThumbsUp);


    /**
     * 新增点赞 
     * 
     * @param okrThumbsUp 点赞 
     * @return 结果
     */
    public int insertOkrThumbsUp(OkrThumbsUp okrThumbsUp);

    /**
     * 修改点赞 
     * 
     * @param okrThumbsUp 点赞 
     * @return 结果
     */
    public int updateOkrThumbsUp(OkrThumbsUp okrThumbsUp);

    /**
     * 批量删除点赞 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrThumbsUpByIds(String ids);

    /**
     * 删除点赞 信息
     * 
     * @param id 点赞 ID
     * @return 结果
     */
    public int deleteOkrThumbsUpById(Long id);
}
