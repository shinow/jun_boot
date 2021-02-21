package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.OkrCommentMeta;
import com.ruoyi.system.mapper.OkrCommentMetaMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrCommentMapper;
import com.ruoyi.system.domain.OkrComment;
import com.ruoyi.system.service.IOkrCommentService;
import com.ruoyi.common.core.text.Convert;

/**
 *  评论 Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-17
 */
@Service
public class OkrCommentServiceImpl implements IOkrCommentService 
{
    @Autowired
    private OkrCommentMapper okrCommentMapper;

    @Autowired
    private OkrCommentMetaMapper okrCommentMetaMapper;

    /**
     * 查询 评论 
     * 
     * @param id  评论 ID
     * @return  评论 
     */
    @Override
    public OkrComment selectOkrCommentById(Long id)
    {
        return okrCommentMapper.selectOkrCommentById(id);
    }

    /**
     * 查询 评论
     *
     * @param okrComment
     * @return  评论
     */
    public OkrComment selectOkrCommentWholeInfo(OkrComment okrComment){
        return okrCommentMapper.selectOkrCommentWholeInfo(okrComment);
    }

    /**
     * 查询 评论 列表
     * 
     * @param okrComment  评论 
     * @return  评论 
     */
    @Override
    public List<OkrComment> selectOkrCommentList(OkrComment okrComment)
    {
        return okrCommentMapper.selectOkrCommentList(okrComment);
    }

    /**
     * 查询评论列表 并且附带额外的回复和附件信息
     *
     * @param okrComment  评论
     * @return  评论
     */
    @Override
    public List<OkrComment> selectOkrCommentMetaList(OkrComment okrComment)
    {
        List<OkrComment> okrCommentLists = selectOkrCommentList(okrComment);
        if (okrCommentLists.size() > 0) {
            for (int i = 0; i < okrCommentLists.size(); i++) {
                OkrComment comment =  okrCommentLists.get(i);
                //获得评论的子集评论：字段type 其中数值为3
                comment.setReplyComments(selectOkrCommentByParentId(comment));
                //获得评论的附件文件
                comment.setMetaComments(selectOkrCommentMetaFiles(comment));
            }
        }
        return okrCommentLists;
    }



    /**
     * 查询二级回复（一级评论的子集评论）
     *
     * @param subComment  评论
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentByParentId(OkrComment subComment){
        return okrCommentMapper.selectOkrCommentByParentId(subComment);
    }

    /**
     * 如果开发，可以根据二级评论的孩子评论，进行评论
     * 本阶段，这个功能没有实现
     * 查询三级回复（二级评论的子集评论）
     * @param childComment
     * @return  评论 集合
     */
    public List<OkrComment> selectOkrCommentByChildId(OkrComment childComment){
        return null;
    }


    /**
     *  获得评论的附加文件
     */
    public List<OkrCommentMeta> selectOkrCommentMetaFiles(OkrComment okrComment){
       return okrCommentMetaMapper.selectOkrCommentMetaFiles(okrComment);
    }

    /**
     * 新增 评论 
     * 
     * @param okrComment  评论 
     * @return 结果
     */
    @Override
    public int insertOkrComment(OkrComment okrComment)
    {
        return okrCommentMapper.insertOkrComment(okrComment);
    }

    /**
     * 修改 评论 
     * 
     * @param okrComment  评论 
     * @return 结果
     */
    @Override
    public int updateOkrComment(OkrComment okrComment)
    {
        return okrCommentMapper.updateOkrComment(okrComment);
    }

    /**
     * 删除 评论 对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrCommentByIds(String ids)
    {
        return okrCommentMapper.deleteOkrCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除 评论 信息
     * 
     * @param id  评论 ID
     * @return 结果
     */
    @Override
    public int deleteOkrCommentById(Long id)
    {
        return okrCommentMapper.deleteOkrCommentById(id);
    }
}
