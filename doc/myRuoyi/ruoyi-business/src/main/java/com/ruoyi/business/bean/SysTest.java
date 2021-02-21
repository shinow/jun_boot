package com.ruoyi.business.bean;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author codeGen
 * @since 2019-01-04
 */
public class SysTest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 真名
     */
    private String realName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public SysTest(String name, String realName) {
        this.name = name;
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "SysTest{" +
        "id=" + id +
        ", name=" + name +
        ", realName=" + realName +
        "}";
    }
}
