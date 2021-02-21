package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.OkrComment;
import com.ruoyi.system.domain.OkrCommentMeta;
import org.apache.ibatis.annotations.Param;

/**
 *   评论附加表Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
public interface OkrCommentMetaMapper 
{
    /**
     * 查询  评论附加表
     * 
     * @param id   评论附加表ID
     * @return   评论附加表
     */
    public OkrCommentMeta selectOkrCommentMetaById(Long id);

    /**
     * 查询 评论附加表
     * @param commentId 评论ID
     * @param metaKey 键名称
     * @return  评论附加表
     */
    public OkrCommentMeta selectOkrCommentMetaByMetaKey(@Param("commentId") Integer commentId,@Param("metaKey")  String metaKey);


    /**
     * 查询  评论附加表列表
     * 
     * @param okrCommentMeta   评论附加表
     * @return   评论附加表集合
     */
    public List<OkrCommentMeta> selectOkrCommentMetaList(OkrCommentMeta okrCommentMeta);


    /**
     * 查询  评论附加表列表
     *
     * @param okrComment   评论附加表
     * @return   评论附加表集合
     */
    public List<OkrCommentMeta> selectOkrCommentMetaFiles(OkrComment okrComment);


    /**
     * 新增  评论附加表
     * 
     * @param okrCommentMeta   评论附加表
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
     * 修改  评论附加表
     * 
     * @param okrCommentMeta   评论附加表
     * @return 结果
     */
    public int updateOkrCommentMeta(OkrCommentMeta okrCommentMeta);

    /**
     * 删除  评论附加表
     * 
     * @param id   评论附加表ID
     * @return 结果
     */
    public int deleteOkrCommentMetaById(Long id);

    /**
     * 批量删除  评论附加表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrCommentMetaByIds(String[] ids);
}
