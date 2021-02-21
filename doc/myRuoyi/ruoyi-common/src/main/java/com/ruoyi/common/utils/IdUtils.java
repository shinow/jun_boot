package com.ruoyi.common.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ruoyi.common.config.Global;

/**
 *本系统id生成策略
 *@author PanMeiCheng
 *@date 2019-01-08
 *@version 1.0
 */
public class IdUtils extends IdWorker{
    /**
     * 本系统自定义 ID,与mybaties-plus配置一致
     * @return 字符串类型
     */
    public static String getSysId(){
        IdType idType = getIdType();
        switch (idType) {
            case ID_WORKER:
            case ID_WORKER_STR:
                return IdWorker.getIdStr();
            case UUID:
                return IdWorker.get32UUID();
            default:
                return null;
            }
        }

    public static IdType getIdType(){
        String idtype = Global.getConfig("mybatis-plus.global-config.db-config.id-type");
        if (idtype==null) return null;
        try {
            return IdType.valueOf(IdType.class,idtype.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
