package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.ConstantsOkr;
import com.ruoyi.system.domain.OkrComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrCommentMetaMapper;
import com.ruoyi.system.domain.OkrCommentMeta;
import com.ruoyi.system.service.IOkrCommentMetaService;
import com.ruoyi.common.core.text.Convert;

/**
 *   评论附加表Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-16
 */
@Service
public class OkrCommentMetaServiceImpl implements IOkrCommentMetaService 
{
    @Autowired
    private OkrCommentMetaMapper okrCommentMetaMapper;

    /**
     * 查询  评论附加表
     * 
     * @param id   评论附加表ID
     * @return   评论附加表
     */
    @Override
    public OkrCommentMeta selectOkrCommentMetaById(Long id)
    {
        return okrCommentMetaMapper.selectOkrCommentMetaById(id);
    }

    /**
     * 查询 评论附加表
     * @param commentId 评论ID
     * @param metaKey 键名称
     * @return  评论附加表
     */
    @Override
    public OkrCommentMeta selectOkrCommentMetaByMetaKey(Integer commentId,String metaKey)
    {
        return okrCommentMetaMapper.selectOkrCommentMetaByMetaKey(commentId,metaKey);
    }

    /**
     * 查询  评论附加表列表
     * 
     * @param okrCommentMeta   评论附加表
     * @return   评论附加表
     */
    @Override
    public List<OkrCommentMeta> selectOkrCommentMetaList(OkrCommentMeta okrCommentMeta)
    {
        return okrCommentMetaMapper.selectOkrCommentMetaList(okrCommentMeta);
    }

    /**
     * 新增  评论附加表
     * 
     * @param okrCommentMeta   评论附加表
     * @return 结果
     */
    @Override
    public int insertOkrCommentMeta(OkrCommentMeta okrCommentMeta)
    {
        return okrCommentMetaMapper.insertOkrCommentMeta(okrCommentMeta);
    }

    /**
     * 新增 评论附加表
     *
     * @param okrCommentMetas  评论附加表
     * @return 结果
     */
    public int insertOkrCommentMetaForeach(List<OkrCommentMeta> okrCommentMetas){
        return okrCommentMetaMapper.insertOkrCommentMetaForeach(okrCommentMetas);
    }

    /**
     * 修改  评论附加表
     * 
     * @param okrCommentMeta   评论附加表
     * @return 结果
     */
    @Override
    public int updateOkrCommentMeta(OkrCommentMeta okrCommentMeta)
    {
        return okrCommentMetaMapper.updateOkrCommentMeta(okrCommentMeta);
    }

    /**
     * 修改 评论附加表
     * 结果自增
     * @param commentId
     * @param metaKey
     * @return 结果
     */
    public int increaseOkrCommentMeta(Integer commentId,String metaKey){

        //只有数据类型的才可以进行自增操作
        if(!ConstantsOkr.COMMENT_META_THUMB.equals(metaKey)){
            throw new RuntimeException("数据调用错误");
        }

        OkrCommentMeta commentMetaExist = okrCommentMetaMapper.selectOkrCommentMetaByMetaKey(commentId, metaKey);
        if(commentMetaExist != null ) {
            //自增
            int newValue = Integer.parseInt(commentMetaExist.getMetaValue()) + 1;
            commentMetaExist.setMetaValue(String.valueOf(newValue));
            return okrCommentMetaMapper.updateOkrCommentMeta(commentMetaExist);
        } else {
            //插入
            commentMetaExist = new OkrCommentMeta();
            commentMetaExist.setCommentId(commentId);
            commentMetaExist.setMetaKey(metaKey);
            commentMetaExist.setMetaValue("1");
            return okrCommentMetaMapper.insertOkrCommentMeta(commentMetaExist);
        }
         
    }

    /**
     * 删除  评论附加表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrCommentMetaByIds(String ids)
    {
        return okrCommentMetaMapper.deleteOkrCommentMetaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除  评论附加表信息
     * 
     * @param id   评论附加表ID
     * @return 结果
     */
    @Override
    public int deleteOkrCommentMetaById(Long id)
    {
        return okrCommentMetaMapper.deleteOkrCommentMetaById(id);
    }
}
