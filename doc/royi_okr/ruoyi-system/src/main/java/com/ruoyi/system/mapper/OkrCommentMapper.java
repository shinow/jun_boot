package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.OkrComment;

/**
 * 评论功能Mapper接口
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-19
 */
public interface OkrCommentMapper
{
    /**
     * 查询评论功能
     *
     * @param id 评论功能ID
     * @return 评论功能
     */
    public OkrComment selectOkrCommentById(Long id);


    /**
     * 查询评论功能
     * @param  okrComment
     * @return 评论功能
     */
    public OkrComment selectOkrCommentWholeInfo(OkrComment okrComment);

    /**
     * 查询评论功能列表
     *
     * @param okrComment 评论功能
     * @return 评论功能集合(一级评论)
     */
    public List<OkrComment> selectOkrCommentList(OkrComment okrComment);

    /**
     * 查询评论功能列表
     *
     * @param okrComment 评论功能
     * @return 评论功能集合(二级评论)
     */
    public List<OkrComment> selectOkrCommentByParentId(OkrComment okrComment);

    /**
     * 查询评论功能列表
     *
     * @param okrComment 评论功能
     * @return 评论功能集合(三级评论)
     */
    public List<OkrComment> selectOkrCommentByChildId(OkrComment okrComment);


    /**
     * 新增评论功能
     *
     * @param okrComment 评论功能
     * @return 结果
     */
    public int insertOkrComment(OkrComment okrComment);

    /**
     * 修改评论功能
     *
     * @param okrComment 评论功能
     * @return 结果
     */
    public int updateOkrComment(OkrComment okrComment);

    /**
     * 删除评论功能
     *
     * @param id 评论功能ID
     * @return 结果
     */
    public int deleteOkrCommentById(Long id);

    /**
     * 批量删除评论功能
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrCommentByIds(String[] ids);
}