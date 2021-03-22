package com.jeasyuicn.rbac.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 soho team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>Description : 权限</li>
 * <li>Version     : 1.0</li>
 * <li>Creation    : 2017年10月26日</li>
 * <li>@author      : ____′↘夏悸 <wmails@126.com></li>
 * </ul>
 * <p>****************************************************************************</p>
 */
@Entity
@Table(name = "rbac_permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Permission parent;

    @Column(nullable = false)
    private String name;

    @Column(name = "permission_key", nullable = false, length = 32)
    private String permissionKey;

    /**
     * 权限类型
     */
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 资源
     */
    private String resource;

    private Boolean enable = false;

    private String description;

    private Integer weight = 0;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
    private List<Permission> children;

    @JsonProperty("text")
    public String getText() {
        return this.name;
    }

    /**
     * 权限类型枚举
     */
    public enum Type {
        MENU("菜单"),
        FUNCTION("功能"),
        BLOCK("区域");

        private String display;

        Type(String display) {
            this.display = display;
        }

        public String display() {
            return display;
        }

        @Override
        public String toString() {
            return this.display + "[" + this.name() + "]";
        }
    }
}
