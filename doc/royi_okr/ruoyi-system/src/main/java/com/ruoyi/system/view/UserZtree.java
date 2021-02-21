package com.ruoyi.system.view;

import com.ruoyi.common.core.domain.Ztree;

/**
 * @Description
 * @Author
 * @Date 2020/5/8 16:16
 **/
public class UserZtree extends Ztree {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
