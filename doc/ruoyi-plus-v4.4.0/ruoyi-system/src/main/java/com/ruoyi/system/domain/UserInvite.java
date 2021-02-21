package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;

public class UserInvite extends BaseEntity {

    private Integer id;

    private String account;

    private String inviteId;

    private String create_time;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInviteId() {
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        this.inviteId = inviteId;
    }

    @Override
    public String toString() {
        return "UserInvite{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", inviteId='" + inviteId + '\'' +
                '}';
    }
}
