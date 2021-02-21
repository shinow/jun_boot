package com.ruoyi.system.domain;

import java.util.List;

/**
 * OKR信息对象 okr_info
 * 用于接收 list 形式的变量
 * @author xiaoshuai2233@sina.com
 * @date 2020-04-28
 */
public class KrInfos
{
    private List<OkrInfo> krInfos;

    public List<OkrInfo> getKrInfos() {
        return krInfos;
    }

    public void setKrInfos(List<OkrInfo> krInfos) {
        this.krInfos = krInfos;
    }
}
