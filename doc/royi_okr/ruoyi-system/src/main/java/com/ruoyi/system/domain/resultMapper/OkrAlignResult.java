package com.ruoyi.system.domain.resultMapper;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @Description
 * @Author
 * @Date 2020/6/8 10:16
 **/
public class OkrAlignResult   extends BaseEntity {

    /** 主键 */
    private Long id;

    private Long okrId;
    /** 父级ID */
    private String parentId;

    private String okrKeys;


    private String rId;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOkrId() {
        return okrId;
    }

    public void setOkrId(Long okrId) {
        this.okrId = okrId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getOkrKeys() {
        return okrKeys;
    }

    public void setOkrKeys(String okrKeys) {
        this.okrKeys = okrKeys;
    }
}
