package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.OkrAlignInfo;

/**
 * OKR对齐关系Service接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
public interface IOkrAlignInfoService 
{
    /**
     * 查询OKR对齐关系
     * 
     * @param id OKR对齐关系ID
     * @return OKR对齐关系
     */
    public OkrAlignInfo selectOkrAlignInfoById(Long id);

    /**
     * 查询OKR对齐关系列表
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return OKR对齐关系集合
     */
    public List<OkrAlignInfo> selectOkrAlignInfoList(OkrAlignInfo okrAlignInfo);


//    /**
//     * 查询OKR对齐状态
//     *
//     * @param okrIds  OKR清单集合
//     * @return OKR对齐关系集合
//     */
//    public List<OkrAlignInfo> selectOkrAlignInfoListForIds(List<String> okrIds);
//
//
//    public Map<String, String> getAlignMap(List<OkrAlignInfo> okrAlignInfos);


    /**
     * 新增OKR对齐关系
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return 结果
     */
    public int insertOkrAlignInfo(OkrAlignInfo okrAlignInfo);

    /**
     * 修改OKR对齐关系
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return 结果
     */
    public int updateOkrAlignInfo(OkrAlignInfo okrAlignInfo);

    /**
     * 批量删除OKR对齐关系
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrAlignInfoByIds(String ids);

    /**
     * 删除OKR对齐关系信息
     * 
     * @param id OKR对齐关系ID
     * @return 结果
     */
    public int deleteOkrAlignInfoById(Long id);
}
