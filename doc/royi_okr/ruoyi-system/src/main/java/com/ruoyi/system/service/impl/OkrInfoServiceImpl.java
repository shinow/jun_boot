package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.domain.OkrProjectUser;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.resultMapper.MyTreeMapResult;
import com.ruoyi.system.domain.resultMapper.OkrAlignResult;
import com.ruoyi.system.domain.resultMapper.OkrProjectRoleResult;
import com.ruoyi.system.domain.resultMapper.SynergyOkrInfo;
import com.ruoyi.system.mapper.OkrInfoMapper;
import com.ruoyi.system.service.IOkrAlignInfoService;
import com.ruoyi.system.service.IOkrInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * OKR信息Service业务层处理
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
@Service
public class OkrInfoServiceImpl implements IOkrInfoService {
    @Autowired
    private OkrInfoMapper okrInfoMapper;

    @Autowired
    private IOkrAlignInfoService okrAlignInfoService;

    /**
     * 查询OKR信息
     *
     * @param id OKR信息ID
     * @return OKR信息
     */
    @Override
    public OkrInfo selectOkrInfoById(Long id) {
        return okrInfoMapper.selectOkrInfoById(id);
    }

    /**
     * 查询OKR目标信息
     *
     * @param parentId OKR信息parentId
     * @return OKR信息
     */
    @Override
    public OkrInfo selectOkrInfoByParentId(String parentId) {
        return okrInfoMapper.selectOkrInfoByParentId(parentId);
    }

    /**
     * 查询最大OKR信息
     *
     * @param okrInfo OKR信息
     * @return OKR信息
     */
    @Override
    public OkrInfo queryMaxOkrInfoByUserId(OkrInfo okrInfo) {
        return okrInfoMapper.queryMaxOkrInfoByUserId(okrInfo);
    }

    @Override
    public OkrInfo queryMaxKeyResultByUserId(OkrInfo okrInfo) {
        return okrInfoMapper.queryMaxKeyResultByUserId(okrInfo);
    }

    /**
     * 查询当前用户参与的项目清单
     *
     * @param params
     * @return
     */
    @Override
    public List<OkrProjectRoleResult> queryProjectListForUserId(Map<String, Long> params) {
        return okrInfoMapper.queryProjectListForUserId(params);
    }

    /**
     * 查询个人OKR
     *
     * @param okrInfo
     * @param userId
     * @return
     */
    @Override
    public List<OkrInfo> selectOkrInfoListByUserId(OkrInfo okrInfo, Long userId) {
        List<OkrInfo> okrList = null;
        if (SysUser.isAdmin(userId)) {
            okrList = okrInfoMapper.selectOkrInfoList(okrInfo);
        } else {
            okrInfo.getParams().put("userId", userId);
            okrList = okrInfoMapper.selectOkrInfoListByUserId(okrInfo);
        }
        return okrList;
    }

    @Override
    public List<OkrInfo> selectOkrInfoListForProject(OkrProjectUser okrProjectUser) {
        return okrInfoMapper.selectOkrInfoListForProject(okrProjectUser);
    }

    @Override
    public List<OkrInfo> selectOkrInfoListByParentId(OkrInfo parentInfo) {
        return okrInfoMapper.selectOkrInfoListByParentId(parentInfo);
    }

    @Override
    public List<OkrInfo> selectOkrInfoListWithSynergyByParentId(OkrInfo okrInfo){
        return okrInfoMapper.selectOkrInfoListWithSynergyByParentId(okrInfo);
    }

    @Override
    public List<OkrInfo> selectOkrInfoTreeMapByUserId(OkrInfo okrInfo) {
        return okrInfoMapper.selectOkrInfoTreeMapByUserId(okrInfo);
    }

    @Override
    public List<OkrInfo> selectOkrInfoChildTreeMapById(Long id) {
        return okrInfoMapper.selectOkrInfoChildTreeMapById(id);
    }

    /**
     * 生成一层视图节点
     *
     * @param okrInfos
     * @return
     */
    @Override
    public List<MyTreeMapResult> queryTreeMapList(List<OkrInfo> okrInfos) {
        List<MyTreeMapResult> treeMapResults = new ArrayList<>();
        for (int i = 0; i < okrInfos.size(); i++) {
            OkrInfo info = okrInfos.get(i);
            if(info!=null)
            {
                MyTreeMapResult treeMapResult = new MyTreeMapResult();
                treeMapResult.setArea(info.getContent());
                treeMapResult.setName(info.getUserName());
                treeMapResult.setOffice(info.getDeptName());
                treeMapResult.setTags("c");
                treeMapResult.setSingleId(UUID.randomUUID().toString());
                treeMapResult.setId(info.getId());
                treeMapResults.add(treeMapResult);
            }
        }
        return treeMapResults;
    }

    /**
     * 生成N层节点
     *
     * @param treeMapResults
     * @return
     */
    @Override
    public List<MyTreeMapResult> querySubTreeMapList(List<MyTreeMapResult> treeMapResults) {
        for (int i = 0; i < treeMapResults.size(); i++) {
            List<OkrInfo> infoList = okrInfoMapper.selectOkrInfoChildTreeMapById(treeMapResults.get(i).getId());
            if (infoList.size() > 0) {
                List<MyTreeMapResult> list2 = queryTreeMapList(infoList);
                if(list2.size()>0)
                {
                    treeMapResults.get(i).setChildren(list2);
                }else
                {
                    treeMapResults.get(i).setChildren(null);
                }
                querySubTreeMapList(treeMapResults.get(i).getChildren());
            }
        }
        return treeMapResults;
    }

    /**
     * 查询个人协同OKR信息（全部的其他用户）
     * @param okrInfo
     * @param userId
     * @return
     */
    @Override
    public List<SynergyOkrInfo> querySynergyOkrInfoListByUserId(OkrInfo okrInfo, Long userId) {
        List<SynergyOkrInfo> okrList = null;
        if (SysUser.isAdmin(userId)) {
            okrList = new ArrayList<>();
        } else {
            okrInfo.getParams().put("userId", userId);
            okrList = okrInfoMapper.querySynergyOkrInfoListByUserId(okrInfo);
        }
        return okrList;
    }


    /**
     * 查询个人协同OKR信息（指定的某个用户）
     * @param okrUserId   从被协同的人列表筛选出只包含okrUserId的列表
     * @param userId  被协同的人
     * @return
     */
    @Override
    public List<SynergyOkrInfo> querySynergyOkrInfoByUserId(Long okrUserId, Long userId){
        List<SynergyOkrInfo> okrList = null;
        if (SysUser.isAdmin(userId)) {
            okrList = new ArrayList<>();
        } else {
            okrList = okrInfoMapper.querySynergyOkrInfoByUserId(okrUserId,userId);
        }
        return okrList;
    }

    /**
     * 检测OKR是否被对齐，设置对齐状态
     *
     * @param list
     * @return
     */
    @Override
    public List<OkrInfo> changeSelectOkr(List<OkrInfo> list, Map<String, String> alignMap) {
        List<OkrInfo> resutlList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OkrInfo okrInfo = list.get(i);
                if (alignMap.containsKey(okrInfo.getId().toString())) {
                    okrInfo.setAlign(true);
                } else {
                    okrInfo.setAlign(false);
                }
                resutlList.add(okrInfo);
            }
        }
        return resutlList;
    }

    /**
     * 查询个人OKR的对齐状态
     *
     * @param okrInfo
     * @return
     */
    @Override
    public List<OkrAlignResult> queryMyOkrAlignListByUserId(OkrInfo okrInfo) {
        return okrInfoMapper.queryMyOkrAlignListByUserId(okrInfo);
    }

    @Override
    public Map<String, String> getMyOkrParentIds(List<OkrAlignResult> list) {
        Map<String, String> map = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OkrAlignResult okrInfo = list.get(i);
                if (okrInfo.getParentId() != null && !StringUtils.isEmpty(okrInfo.getParentId())) {
                    map.put(okrInfo.getParentId(), okrInfo.getId().toString());
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getMyOkrIds(List<OkrAlignResult> list) {
        Map<String, String> map = new HashMap<>();
        if (list != null && list.size() > 0) {
            List<String> okrList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                OkrAlignResult okrInfo = list.get(i);
                if (okrInfo != null && okrInfo.getOkrKeys() != null) {
                    String[] keyArr = okrInfo.getOkrKeys().split(",");
                    List<String> keyList = Arrays.asList(keyArr);
                    List arrList = new ArrayList(keyList);
                    String okrId = String.valueOf(okrInfo.getOkrId());
                    arrList.add(okrId);
                    okrList.addAll(arrList);
                }
            }
            for (int j = 0; j < okrList.size(); j++) {
                map.put(okrList.get(j), okrList.get(j));
            }
        }
        return map;
    }

    /**
     * 查询OKR信息列表
     *
     * @param okrInfo OKR信息
     * @return OKR信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<OkrInfo> selectOkrInfoList(OkrInfo okrInfo) {
        return okrInfoMapper.selectOkrInfoList(okrInfo);
    }

    /**
     * 新增OKR信息
     *
     * @param ids OKR信息
     * @return 结果
     */
    @Override
    public List<OkrInfo> queryOkrInfoByIds(List<String> ids) {
        return okrInfoMapper.queryOkrInfoByIds(ids);
    }

    /**
     * 新增OKR信息
     *
     * @param okrInfo OKR信息
     * @return 结果
     */
    @Override
    public int insertOkrInfo(OkrInfo okrInfo) {
        okrInfo.setCreateTime(DateUtils.getNowDate());
        okrInfo.setUpdateTime(DateUtils.getNowDate());
        return okrInfoMapper.insertOkrInfo(okrInfo);
    }

    /**
     * 批量新增OKR信息
     *
     * @param list OKR信息
     * @return 结果
     */
    @Override
    public int insertOkrList(List<OkrInfo> list) {
        return okrInfoMapper.insertOkrList(list);
    }

    /**
     * 修改OKR信息
     *
     * @param okrInfo OKR信息
     * @return 结果
     */
    @Override
    public int updateOkrInfo(OkrInfo okrInfo) {
        return okrInfoMapper.updateOkrInfo(okrInfo);
    }

    /**
     * 更新OKR后更新 O 的信息
     *
     * @param okrInfo ： 被删除 或者 被修改  或者被添加 的okrInfo
     * @return int
     */
    @Override
    public int updateOInfo(OkrInfo okrInfo) {
        //获得本okrInfo　的 Object
        OkrInfo oInfo  = selectOkrInfoByParentId( okrInfo.getParentId());
        //根据 O 获得所有的 kr 信息
        List<OkrInfo> okrInfos = selectOkrInfoListByParentId(oInfo);
        Map objectAvg = okrInfoMapper.selectObjectAvgResult(okrInfo);
        if(objectAvg != null ){

            //处理特需字段  0.0  => 0
            String _confidence = objectAvg.get("confidence_star").toString();
            String confidence  = _confidence.equals("0.0")?"0":_confidence;
            String _process    = objectAvg.get("process").toString();
            String process     = _confidence.equals("0.0")?"0":_process;
            String _score      = objectAvg.get("score_star").toString();
            String score       = _score.equals("0.0")?"0":_score;

            oInfo.setUpdateTime(DateUtils.getNowDate());
            oInfo.setScoreStar( score ) ;                   // 目标评分
            oInfo.setProcess( process );                    // 目标进度
            oInfo.setConfidenceStar( confidence );          // 信心指数
            oInfo.setUpdateTime( DateUtils.getNowDate() );  // 更新时间

        }else{

            oInfo.setUpdateTime(DateUtils.getNowDate());
            oInfo.setScoreStar("0") ;                       // 目标评分
            oInfo.setProcess("0");                          // 目标进度
            oInfo.setConfidenceStar("0");                   // 信心指数
            oInfo.setUpdateTime( DateUtils.getNowDate() );  // 更新时间
        }

        return  updateOkrInfo(oInfo);
    }

    /**
     * 删除OKR信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOkrInfoByIds(String ids) {
        return okrInfoMapper.deleteOkrInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除OKR信息信息
     *
     * @param id OKR信息ID
     * @return 结果
     */
    @Override
    public int deleteOkrInfoById(Long id) {
        return okrInfoMapper.deleteOkrInfoById(id);
    }
}
