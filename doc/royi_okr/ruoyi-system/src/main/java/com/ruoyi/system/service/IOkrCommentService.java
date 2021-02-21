package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OkrComment;
import com.ruoyi.system.domain.OkrCommentMeta;
import org.apache.ibatis.annotations.Param;

/**
 *  评论 Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-17
 */
public interface IOkrCommentService 
{
    /**
     * 查询 评论 
     * 
     * @param id  评论 ID
     * @return  评论 
     */
    public OkrComment selectOkrCommentById(Long id);

    /**
     * 查询 评论
     *
     * @param okrComment
     * @return  评论
     */
    public OkrComment selectOkrCommentWholeInfo(OkrComment okrComment);


    /**
     * 查询 评论 列表
     * 
     * @param okrComment  评论 
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentList(OkrComment okrComment);


    /**
     * 查询评论列表 并且附带额外的回复和附件信息
     *
     * @param okrComment  评论
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentMetaList(OkrComment okrComment);

    /**
     * 查询二级回复（一级评论的子集评论）
     *
     * @param subComment  评论
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentByParentId(OkrComment subComment);


    /**
     * 如果开发，可以根据二级评论的孩子评论，进行评论
     * 本阶段，这个功能没有实现
     * 查询三级回复（二级评论的子集评论）
     * @param childComment
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentByChildId(OkrComment childComment);


    /**
     *  获得评论的附加文件
     */
    public List<OkrCommentMeta> selectOkrCommentMetaFiles(OkrComment okrComment);

    /**
     * 新增 评论 
     * 
     * @param okrComment  评论 
     * @return 结果
     */
    public int insertOkrComment(OkrComment okrComment);

    /**
     * 修改 评论 
     * 
     * @param okrComment  评论 
     * @return 结果
     */
    public int updateOkrComment(OkrComment okrComment);

    /**
     * 批量删除 评论 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrCommentByIds(String ids);

    /**
     * 删除 评论 信息
     * 
     * @param id  评论 ID
     * @return 结果
     */
    public int deleteOkrCommentById(Long id);
}
