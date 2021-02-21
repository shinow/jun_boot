package com.ruoyi.cms.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.cms.mapper.UserSignInMapper;
import com.ruoyi.cms.domain.UserSignIn;
import com.ruoyi.cms.service.IUserSignInService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户签到Service业务层处理
 * 
 * @author markbro
 * @date 2020-02-03
 */
@Service
public class UserSignInServiceImpl implements IUserSignInService 
{
    @Autowired
    private UserSignInMapper userSignInMapper;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询用户签到
     * 
     * @param id 用户签到ID
     * @return 用户签到
     */
    @Override
    public UserSignIn selectUserSignInById(Long id)
    {
        return userSignInMapper.selectUserSignInById(id);
    }

    /**
     * 查询用户签到列表
     * 
     * @param userSignIn 用户签到
     * @return 用户签到
     */
    @Override
    public List<UserSignIn> selectUserSignInList(UserSignIn userSignIn)
    {
        return userSignInMapper.selectUserSignInList(userSignIn);
    }

    /**
     * 新增用户签到
     * 
     * @param userSignIn 用户签到
     * @return 结果
     */
    @Override
    public int insertUserSignIn(UserSignIn userSignIn)
    {
        return userSignInMapper.insertUserSignIn(userSignIn);
    }

    /**
     * 修改用户签到
     * 
     * @param userSignIn 用户签到
     * @return 结果
     */
    @Override
    public int updateUserSignIn(UserSignIn userSignIn)
    {
        return userSignInMapper.updateUserSignIn(userSignIn);
    }

    /**
     * 删除用户签到对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserSignInByIds(String ids)
    {
        return userSignInMapper.deleteUserSignInByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户签到信息
     * 
     * @param id 用户签到ID
     * @return 结果
     */
    @Override
    public int deleteUserSignInById(Long id)
    {
        return userSignInMapper.deleteUserSignInById(id);
    }


    private UserSignIn getByDate(String userId,String dateStr){
        return userSignInMapper.getByDate(userId,dateStr);
    }
    //用户签到
    @Override
    public AjaxResult signIn(String userId){

        //查询今天是否签到
        String month=DateUtils.parseDateToStr("yyyy-MM",new Date());
        String todayStr=DateUtils.getDate();
        UserSignIn today=getByDate(userId,todayStr);
        if(today!=null){
            //今天已经签到
            return AjaxResult.error("今天已经签到!");
        }

        today=new UserSignIn();
        today.setUserId(userId);
        today.setMonth(month);
        today.setSignTime(new Date());
        //获取最近一次签到记录，用于累计签到次数
        UserSignIn last=userSignInMapper.getLast(userId);
        if(last!=null){
            today.setTotalCount(last.getTotalCount()+1);
            if(last.getMonth().equals(month)){//如果上次月份是本月
                today.setMonthCount(last.getMonthCount()+1);
            }else{
                today.setMonthCount(1L);
            }

            if(this.formatDate(last.getSignTime()).contains(this.formatDate(org.apache.commons.lang3.time.DateUtils.addDays(new Date(),-1)))){
                //上次签到时间是昨天，那么说明没间断
                today.setConstantCount(last.getConstantCount()+1);
            }else{
                today.setConstantCount(1L);
            }

        }else{
            today.setTotalCount(1L);//累计签到数
            today.setMonthCount(1L);//本月累计签到数
            today.setConstantCount(1L);//持续不间断签到数
        }
        this.insertUserSignIn(today);

        //插入用户签到积分
        Long score =today.getConstantCount();
        if(score>7L){
            score=7L;
        }
        SysUser user= ShiroUtils.getSysUser();
        user=userService.selectUserById(user.getUserId());
        user.setScore(user.getScore()+Integer.valueOf(score.toString()));
        int n =userService.updateUserInfo(user);
        if(n>0){
            ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
        }

        //获得本月签到记录
        String signList =this.getSignStringByMonth(userId, month);
        return AjaxResult.success("签到成功!",signList);
    }

    //获得某个月份的签到记录month 格式要求 yyyy-MM
    public String getSignStringByMonth(String userId,String month){

        List<UserSignIn> list=userSignInMapper.findByMonth(userId,month);
        //获得本月天数
        String yearStr=month.substring(0,4);
        String monthStr=month.substring(5,7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int count= 0;
        try {
            count = this.getDaysOfMonth(sdf.parse(yearStr+"-"+monthStr+"-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(monthStr.startsWith("0")){
            monthStr=monthStr.substring(1,2);
        }

        //遍历该月的每一天
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.parseDate(month+"-01"));
        //cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        String signList="";
        for (int i = 0; i < count; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            String dateStr=this.formatDate(d);
            if(CollectionUtils.isNotEmpty(list)) {
                List<UserSignIn> tempList= list.stream().filter(p -> this.formatDate(p.getSignTime()).contains(dateStr)).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(tempList)){
                    signList+="1,";
                }else{
                    signList+="0,";
                }
            }else{//本月还没有签到
                signList+="0,";
            }
        }
        signList=signList.substring(0,signList.length()-1);//去掉最后一个逗号
        return signList;
    }
    private List<UserSignIn> findByMonth(String userId,String month){
        return userSignInMapper.findByMonth(userId,month);
    }
    public  int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  sdf.format(date);
    }

    @Override
    //初始化签到界面的值
    public AjaxResult initSignPage(String userId){
        Map resultMap=new HashMap<String,Object>();
        UserSignIn last=userSignInMapper.getLast(userId);
        if(last!=null){
            String todayStr=DateUtils.getDate();
            String month=DateUtils.parseDateToStr("yyyy-MM",new Date());
            if(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss",last.getSignTime()).contains(todayStr)){
                resultMap.put("sign_today","1");//今日已经签到
            }else{
                resultMap.put("sign_today","0");//今日未签到
            }

            resultMap.put("Constant_count", last.getConstantCount());//持续签到
            resultMap.put("Month_count",last.getMonthCount());//本月签到
            resultMap.put("Total_count",last.getTotalCount());//总计签到
            //获得本月签到记录
            String  signString =this.getSignStringByMonth(userId, month);
            resultMap.put("signString",signString);
            return  AjaxResult.success(resultMap);
        }else{
            String todayStr=DateUtils.getDate();
            String month=DateUtils.parseDateToStr("yyyy-MM",new Date());

            resultMap.put("sign_today","0");//今日未签到

            resultMap.put("Constant_count", 0);//持续签到
            resultMap.put("Month_count",0);//本月签到
            resultMap.put("Total_count",0);//总计签到
            //获得本月签到记录
            String  signString =this.getSignStringByMonth(userId, month);
            resultMap.put("signString",signString);
            return  AjaxResult.success(resultMap);
        }

    }

}
