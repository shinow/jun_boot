package com.ruoyi.es.bean.mysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author japhet_jiu
 * @version 1.0
 */
@Data
@Table(name = "user_info")
@Entity
public class UserBean {
    @Id //标识主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private String phone;
    @Column(columnDefinition = "mediumtext")  //当数据库该字段特别大的时候， 设置参数  数据库的类型
    private String remark;
    private Date createTime;
    private Date updateTime;
}