package com.jeasyuicn.rbac.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : com.jeasyuicn.rbac.model.entity</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年10月26日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Entity
@Table(name = "rbac_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,length = 16,nullable = false)
    private String account;

    @Column(nullable = false,length = 128)
    @JsonIgnore
    private String password;

    @Column(name = "user_name",length = 32)
    private String userName;

    private String tel;

    private Boolean enable = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rbac_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
