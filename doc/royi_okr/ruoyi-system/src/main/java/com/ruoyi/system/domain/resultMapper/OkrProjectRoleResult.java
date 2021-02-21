package com.ruoyi.system.domain.resultMapper;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @Description
 * @Author
 * @Date 2020/6/5 10:01
 **/
public class OkrProjectRoleResult extends BaseEntity {

    /** 项目编码 */
    private String projectCode;

    /** 用户标识 */
    private Long userId;

    /** OKR主键标识 */
    private Long okrId;

    private boolean selected;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOkrId() {
        return okrId;
    }

    public void setOkrId(Long okrId) {
        this.okrId = okrId;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
