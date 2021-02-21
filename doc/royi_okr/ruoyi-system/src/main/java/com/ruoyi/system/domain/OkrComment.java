package com.ruoyi.system.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ruoyi.common.utils.CommentTimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 评论功能对象 okr_comment
 *
 * @author xiaoshuai2233@sina.com
 * @date 2020-10-19
 */
public class OkrComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论 */
    private Long id;

    /** 评论人id */
    @Excel(name = "评论人id")
    private Long userId;

    /** 评论人头像 */
    private String avatar;

    /** 评论人用户名 */
    private String userName;

    /** 评论的对象 ID */
    @Excel(name = "评论的OKR ID")
    private Long objectId;

    /** 评论父类 */
    @Excel(name = "评论父类")
    private Long parentId;

    /** 评论的具体内容 */
    @Excel(name = "评论的具体内容")
    private String content;

    /** 评论者IP */
    @Excel(name = "评论者IP")
    private String userIp;

    /** 评论的点赞数据 */
    @Excel(name = "评论的点赞数据：")
    private Integer thumbNum;

    /** 评论的类型：（0 :关键目标，1 :关键结果） */
    @Excel(name = "评论的类型：", readConverterExp = "0=,:=关键目标，1,:=关键结果")
    private Integer type;

    /** 状态（0:默认、1:删除） */
    @Excel(name = "状态", readConverterExp = "0=:默认、1:删除")
    private Integer status;

    /** 评论时间 */
    @Excel(name = "评论时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 评论展示时间 */
    private String addShowTime;

    /** 子集评论 */
    private List<OkrComment> replyComments = new ArrayList<>();

    /** 当前评论Meta字段信息 */
    private List<OkrCommentMeta> metaComments = new LinkedList<>();

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setObjectId(Long objectId)
    {
        this.objectId = objectId;
    }

    public Long getObjectId()
    {
        return objectId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setUserIp(String userIp)
    {
        this.userIp = userIp;
    }

    public String getUserIp()
    {
        return userIp;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    public String getAddShowTime() {
        return addShowTime;
    }

    public Integer getThumbNum() {
        return thumbNum;
    }

    public void setThumbNum(Integer thumbNum) {
        this.thumbNum = thumbNum;
    }

    public void setAddShowTime(String addTime) {
        this.addShowTime = CommentTimeUtil.putDate( addTime );
    }

    public List<OkrComment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<OkrComment> replyComments) {
        this.replyComments = replyComments;
    }

    public List<OkrCommentMeta> getMetaComments() {
        return metaComments;
    }

    public void setMetaComments(List<OkrCommentMeta> metaComments) {
        this.metaComments = metaComments;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("objectId", getObjectId())
                .append("parentId", getParentId())
                .append("content", getContent())
                .append("userIp", getUserIp())
                .append("type", getType())
                .append("status", getStatus())
                .append("addTime", getAddTime())
                .toString();
    }
}