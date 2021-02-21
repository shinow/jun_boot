package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OkrAlignInfoMapper;
import com.ruoyi.system.domain.OkrAlignInfo;
import com.ruoyi.system.service.IOkrAlignInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * OKR对齐关系Service业务层处理
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-06-04
 */
@Service
public class OkrAlignInfoServiceImpl implements IOkrAlignInfoService 
{
    @Autowired
    private OkrAlignInfoMapper okrAlignInfoMapper;

    /**
     * 查询OKR对齐关系
     * 
     * @param id OKR对齐关系ID
     * @return OKR对齐关系
     */
    @Override
    public OkrAlignInfo selectOkrAlignInfoById(Long id)
    {
        return okrAlignInfoMapper.selectOkrAlignInfoById(id);
    }

    /**
     * 查询OKR对齐关系列表
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return OKR对齐关系
     */
    @Override
    public List<OkrAlignInfo> selectOkrAlignInfoList(OkrAlignInfo okrAlignInfo)
    {
        return okrAlignInfoMapper.selectOkrAlignInfoList(okrAlignInfo);
    }

//    @Override
//    public List<OkrAlignInfo> selectOkrAlignInfoListForIds(List<String> okrIds) {
//        return okrAlignInfoMapper.selectOkrAlignInfoListForIds(okrIds);
//    }
//
//
//
//    @Override
//    public Map<String, String> getAlignMap(List<OkrAlignInfo> okrAlignInfos) {
//        Map<String, String> alignMap = new HashMap<>();
//        if (okrAlignInfos != null && okrAlignInfos.size() > 0) {
//            for (int i = 0; i < okrAlignInfos.size(); i++) {
//                //放O
//                alignMap.put(okrAlignInfos.get(i).getOkrId().toString(), "");
//                String[] keys = okrAlignInfos.get(i).getOkrKeys().split(",");
//                for(int j=0;j<keys.length;j++)
//                {
//                    //放Key
//                    alignMap.put(keys[j],"");
//                }
//            }
//        }
//        return alignMap;
//    }

    /**
     * 新增OKR对齐关系
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return 结果
     */
    @Override
    public int insertOkrAlignInfo(OkrAlignInfo okrAlignInfo)
    {
        return okrAlignInfoMapper.insertOkrAlignInfo(okrAlignInfo);
    }

    /**
     * 修改OKR对齐关系
     * 
     * @param okrAlignInfo OKR对齐关系
     * @return 结果
     */
    @Override
    public int updateOkrAlignInfo(OkrAlignInfo okrAlignInfo)
    {
        return okrAlignInfoMapper.updateOkrAlignInfo(okrAlignInfo);
    }

    /**
     * 删除OKR对齐关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrAlignInfoByIds(String ids)
    {
        return okrAlignInfoMapper.deleteOkrAlignInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除OKR对齐关系信息
     * 
     * @param id OKR对齐关系ID
     * @return 结果
     */
    @Override
    public int deleteOkrAlignInfoById(Long id)
    {
        return okrAlignInfoMapper.deleteOkrAlignInfoById(id);
    }
}
