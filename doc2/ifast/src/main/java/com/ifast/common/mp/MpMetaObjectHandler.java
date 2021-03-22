package com.ifast.common.mp;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.ifast.common.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * <pre>
 *
 * </pre>
 * <small> 2019-06-06 16:26 | Aron</small>
 */
@Slf4j
public class MpMetaObjectHandler extends MetaObjectHandler {

    private final static String COLUMN_CREATE_AT = "createAt";
    private final static String COLUMN_CREATE_BY = "createBy";
    private final static String COLUMN_UPDATE_AT = "updateAt";
    private final static String COLUMN_UPDATE_BY = "updateBy";


    @Override
    public void insertFill(MetaObject metaObject) {

        Object createTime = getFieldValByName(COLUMN_CREATE_AT, metaObject);
        Date now = new Date();
        if (createTime == null) {
            if(log.isDebugEnabled()){
                log.debug("set COLUMN_CREATE_AT = " + now);
            }
            setFieldValByName(COLUMN_CREATE_AT, now, metaObject);
        }

        Object createBy = getFieldValByName(COLUMN_CREATE_BY, metaObject);
        if (createBy == null) {
            Long userId = getUserId();
            if(log.isDebugEnabled()){
                log.debug("set COLUMN_CREATE_BY = " + userId);
            }
            setFieldValByName(COLUMN_CREATE_BY, userId, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        Object updateTime = getFieldValByName(COLUMN_UPDATE_AT, metaObject);
        Date now = new Date();
        if (updateTime == null) {
            if(log.isDebugEnabled()){
                log.debug("set COLUMN_UPDATE_AT = " + now);
            }
            setFieldValByName(COLUMN_UPDATE_AT, now, metaObject);
        }

        Object updateBy = getFieldValByName(COLUMN_UPDATE_BY, metaObject);
        if (updateBy == null) {
            Long userId = getUserId();
            if(log.isDebugEnabled()){
                log.debug("set COLUMN_UPDATE_BY = " + userId);
            }
            setFieldValByName(COLUMN_UPDATE_BY, userId, metaObject);
        }

    }

    private Long getUserId() {
        return ShiroUtils.getUserId();
    }
}
