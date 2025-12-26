package com.teach.teachingsys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 帖子对象 post
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Post extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子ID */
    private Long id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 发帖用户ID */
    @Excel(name = "发帖用户ID")
    private Long userId;

    /** 发帖人姓名(冗余) */
    @Excel(name = "发帖人姓名(冗余)")
    private String authorName;

    /** 发帖人头像URL(冗余) */
    @Excel(name = "发帖人头像URL(冗余)")
    private String authorAvatar;

    /** 帖子标题 */
    @Excel(name = "帖子标题")
    private String title;

    /** 帖子内容 */
    @Excel(name = "帖子内容")
    private String content;

    /** 帖子类型 */
    @Excel(name = "帖子类型")
    private String postType;

    /** 帖子状态 */
    @Excel(name = "帖子状态")
    private String status;

    /** 查看次数 */
    @Excel(name = "查看次数")
    private Long viewCount;

    /** 回复次数 */
    @Excel(name = "回复次数")
    private Long replyCount;

    /** 热度分数(计算字段) */
    @Excel(name = "热度分数(计算字段)")
    private Long hotScore;

    /** 统计信息更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计信息更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsUpdatedTime;

    /** 最后回复时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastReplyTime;

    /** 最后回复人姓名 */
    @Excel(name = "最后回复人姓名")
    private String lastReplyAuthorName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setAuthorName(String authorName) 
    {
        this.authorName = authorName;
    }

    public String getAuthorName() 
    {
        return authorName;
    }

    public void setAuthorAvatar(String authorAvatar) 
    {
        this.authorAvatar = authorAvatar;
    }

    public String getAuthorAvatar() 
    {
        return authorAvatar;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setPostType(String postType) 
    {
        this.postType = postType;
    }

    public String getPostType() 
    {
        return postType;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setViewCount(Long viewCount) 
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount() 
    {
        return viewCount;
    }

    public void setReplyCount(Long replyCount) 
    {
        this.replyCount = replyCount;
    }

    public Long getReplyCount() 
    {
        return replyCount;
    }

    public void setHotScore(Long hotScore) 
    {
        this.hotScore = hotScore;
    }

    public Long getHotScore() 
    {
        return hotScore;
    }

    public void setStatsUpdatedTime(Date statsUpdatedTime) 
    {
        this.statsUpdatedTime = statsUpdatedTime;
    }

    public Date getStatsUpdatedTime() 
    {
        return statsUpdatedTime;
    }

    public void setLastReplyTime(Date lastReplyTime) 
    {
        this.lastReplyTime = lastReplyTime;
    }

    public Date getLastReplyTime() 
    {
        return lastReplyTime;
    }

    public void setLastReplyAuthorName(String lastReplyAuthorName) 
    {
        this.lastReplyAuthorName = lastReplyAuthorName;
    }

    public String getLastReplyAuthorName() 
    {
        return lastReplyAuthorName;
    }

    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("userId", getUserId())
            .append("authorName", getAuthorName())
            .append("authorAvatar", getAuthorAvatar())
            .append("title", getTitle())
            .append("content", getContent())
            .append("postType", getPostType())
            .append("status", getStatus())
            .append("viewCount", getViewCount())
            .append("replyCount", getReplyCount())
            .append("hotScore", getHotScore())
            .append("statsUpdatedTime", getStatsUpdatedTime())
            .append("lastReplyTime", getLastReplyTime())
            .append("lastReplyAuthorName", getLastReplyAuthorName())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
