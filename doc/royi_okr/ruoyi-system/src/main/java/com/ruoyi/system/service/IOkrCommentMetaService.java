package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.OkrComment;
import com.ruoyi.system.domain.OkrCommentMeta;

/**
 * 评论附加Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
public interface IOkrCommentMetaService 
{
    /**
     * 查询 评论附加表
     * 
     * @param id  评论附加表ID
     * @return  评论附加表
     */
    public OkrCommentMeta selectOkrCommentMetaById(Long id);

    /**
     * 查询 评论附加表
     * @param commentId 评论ID
     * @param key 键名称
     * @return  评论附加表
     */
    public OkrCommentMeta selectOkrCommentMetaByMetaKey(Integer commentId,String key);

    /**
     * 查询 评论附加表列表
     * 
     * @param okrCommentMeta  评论附加表
     * @return  评论附加表集合
     */
    public List<OkrCommentMeta> selectOkrCommentMetaList(OkrCommentMeta okrCommentMeta);

    /**
     * 新增 评论附加表
     * 
     * @param okrCommentMeta  评论附加表
     * @return 结果
     */
    public int insertOkrCommentMeta(OkrCommentMeta okrCommentMeta);

    /**
     * 新增 评论附加表
     *
     * @param okrCommentMetas  评论附加表
     * @return 结果
     */
    public int insertOkrCommentMetaForeach(List<OkrCommentMeta> okrCommentMetas);


    /**
     * 修改 评论附加表
     * 
     * @param okrCommentMeta  评论附加表
     * @return 结果
     */
    public int updateOkrCommentMeta(OkrCommentMeta okrCommentMeta);


    /**
     * 修改 评论附加表
     * 结果自增
     * @param commentId
     * @param metaKey
     * @return 结果
     */
    public int increaseOkrCommentMeta(Integer commentId,String metaKey);

    /**
     * 批量删除 评论附加表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrCommentMetaByIds(String ids);

    /**
     * 删除 评论附加表信息
     * 
     * @param id  评论附加表ID
     * @return 结果
     */
    public int deleteOkrCommentMetaById(Long id);
     
}
