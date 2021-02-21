package com.ruoyi.system.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.OkrInfo;
import com.ruoyi.system.domain.OkrProjectUser;
import com.ruoyi.system.domain.resultMapper.OkrAlignResult;
import com.ruoyi.system.domain.resultMapper.OkrProjectRoleResult;
import com.ruoyi.system.domain.resultMapper.SynergyOkrInfo;

/**
 * OKR信息Mapper接口
 * 
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
public interface OkrInfoMapper 
{
    /**
     * 查询OKR信息
     * 
     * @param id OKR信息ID
     * @return OKR信息
     */
    public OkrInfo selectOkrInfoById(Long id);



    /**
     * 查询OKR目标信息
     *
     * @param parentId OKR信息parentId
     * @return OKR信息
     */
    public OkrInfo selectOkrInfoByParentId(String parentId);
    /**
     * 查询最大OKR信息
     *
     * @param okrInfo OKR信息
     * @return OKR信息
     */
    public OkrInfo queryMaxOkrInfoByUserId(OkrInfo okrInfo);



    /**
     * 查询最大KR信息
     *
     * @param okrInfo OKR信息
     * @return OKR信息
     */
    public OkrInfo queryMaxKeyResultByUserId(OkrInfo okrInfo);

    /**
     *  查询当前用户参与的项目清单
     * @param userId
     * @return
     */
    public List<OkrProjectRoleResult> queryProjectListForUserId(Map<String,Long> params);
    /**
     * 查询个人OKR信息
     * @param okrInfo
     * @return
     */
    public List<OkrInfo> selectOkrInfoListByUserId(OkrInfo okrInfo);


    /**
     * 查询组结构的OKR清单信息
     * @param okrProjectUser
     * @return
     */
    public List<OkrInfo> selectOkrInfoListForProject(OkrProjectUser okrProjectUser);


    /**
     * 修改OKR信息
     *
     * @param okrInfo OKR信息
     * @return 结果
     */
    public Map selectObjectAvgResult(OkrInfo okrInfo);

    /**
     * 通过O Id查询KR清单
     * @param parentInfo
     * @return
     */
    public List<OkrInfo> selectOkrInfoListByParentId(OkrInfo parentInfo);

    /**
     * 通过O Id查询KR清单
     * @param okrInfo
     * @return
     */
    public List<OkrInfo> selectOkrInfoListWithSynergyByParentId(OkrInfo okrInfo);

    /**
     * 视图 子 元素集合
     * @param id
     * @return
     */
    public List<OkrInfo> selectOkrInfoChildTreeMapById(Long id);

    /**
     * 查询个人协同OKR信息（全部的其他用户）
     * 查询个人OKR信息
     * @param okrInfo
     * @return
     */
    public List<SynergyOkrInfo> querySynergyOkrInfoListByUserId(OkrInfo okrInfo);

    /**
     * 查询个人协同OKR信息（指定的某个用户）
     * 查询个人OKR信息
     * @param okrUserId   从被协同的人列表筛选出只包含okrUserId的列表
     * @param userId  被协同的人
     * @return
     */
    public List<SynergyOkrInfo> querySynergyOkrInfoByUserId(Long okrUserId,Long userId);

    /**
     * @param okrInfo
     * @return
     */
    public List<OkrInfo> selectOkrInfoTreeMapByUserId(OkrInfo okrInfo);

    /**
     * 查询OKR信息列表
     * 
     * @param okrInfo OKR信息
     * @return OKR信息集合
     */
    public List<OkrInfo> selectOkrInfoList(OkrInfo okrInfo);

    /**
     * 查询个人协同OKR信息是否对齐
     * @param okrInfo
     * @return
     */
    public List<OkrAlignResult> queryMyOkrAlignListByUserId(OkrInfo okrInfo);



    /**
     * 批量插叙OKR信息
     *
     * @param ids 需要查询的数据ID
     * @return 结果
     */
    public List<OkrInfo>  queryOkrInfoByIds(List<String> ids);


    /**
     * 新增OKR信息
     * 
     * @param okrInfo OKR信息
     * @return 结果
     */
    public int insertOkrInfo(OkrInfo okrInfo);

    /**
     * 批量新增OKR信息
     *
     * @param list OKR信息
     * @return 结果
     */
    public int insertOkrList(List<OkrInfo> list);


    /**
     * 修改OKR信息
     * 
     * @param okrInfo OKR信息
     * @return 结果
     */
    public int updateOkrInfo(OkrInfo okrInfo);


    /**
     * 删除OKR信息
     * 
     * @param id OKR信息ID
     * @return 结果
     */
    public int deleteOkrInfoById(Long id);

    /**
     * 批量删除OKR信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOkrInfoByIds(String[] ids);
}
