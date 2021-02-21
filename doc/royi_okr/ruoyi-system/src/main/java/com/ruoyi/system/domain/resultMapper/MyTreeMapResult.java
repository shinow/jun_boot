package com.ruoyi.system.domain.resultMapper;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date 2020/6/11 15:31
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyTreeMapResult  {

    private Long id;
    private String name;
    private String singleId;
    private String imageUrl;
    private String area;
    private String profileUrl;
    private String office;
    private String tags;
    private String isLoggedUser;
    private UnitClass unit;
    private String positionName;
    private List<MyTreeMapResult> children;


    public String getSingleId() {
        return singleId;
    }

    public void setSingleId(String singleId) {
        this.singleId = singleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIsLoggedUser() {
        return isLoggedUser;
    }

    public void setIsLoggedUser(String isLoggedUser) {
        this.isLoggedUser = isLoggedUser;
    }

    public UnitClass getUnit() {
        return unit;
    }

    public void setUnit(UnitClass unit) {
        this.unit = unit;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public List<MyTreeMapResult> getChildren() {
        return children;
    }

    public void setChildren(List<MyTreeMapResult> children) {
        this.children = children;
    }
}
