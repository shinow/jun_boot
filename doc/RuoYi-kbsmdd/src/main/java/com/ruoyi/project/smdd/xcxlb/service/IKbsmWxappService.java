package com.ruoyi.project.smdd.xcxlb.service;

import java.util.List;
import com.ruoyi.project.smdd.xcxlb.domain.KbsmWxapp;

/**
 * 小程序列表Service接口
 * 
 * @author ruoyi
 * @date 2020-08-08
 */
public interface IKbsmWxappService 
{
    /**
     * 查询小程序列表
     * 
     * @param id 小程序列表ID
     * @return 小程序列表
     */
    public KbsmWxapp selectKbsmWxappById(Long id);

    /**
     * 查询小程序列表列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 小程序列表集合
     */
    public List<KbsmWxapp> selectKbsmWxappList(KbsmWxapp kbsmWxapp);

    /**
     * 新增小程序列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 结果
     */
    public int insertKbsmWxapp(KbsmWxapp kbsmWxapp);

    /**
     * 修改小程序列表
     * 
     * @param kbsmWxapp 小程序列表
     * @return 结果
     */
    public int updateKbsmWxapp(KbsmWxapp kbsmWxapp);

    /**
     * 批量删除小程序列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKbsmWxappByIds(String ids);

    /**
     * 删除小程序列表信息
     * 
     * @param id 小程序列表ID
     * @return 结果
     */
    public int deleteKbsmWxappById(Long id);
}
