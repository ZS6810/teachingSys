package com.teach.teachingsys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户课程关系对象 user_course
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class UserCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long id;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long userId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrolledTime;

    /** 学习进度百分比 */
    @Excel(name = "学习进度百分比")
    private BigDecimal progressRate;

    /** 已完成章节数 */
    @Excel(name = "已完成章节数")
    private Long completedChapters;

    /** 总章节数 */
    @Excel(name = "总章节数")
    private Long totalChapters;

    /** 已完成资料数 */
    @Excel(name = "已完成资料数")
    private Long completedMaterials;

    /** 总资料数 */
    @Excel(name = "总资料数")
    private Long totalMaterials;

    /** 进度更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "进度更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date progressUpdatedTime;

    /** 最后访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后访问时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastAccessed;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionTime;

    /** 学习状态 */
    @Excel(name = "学习状态")
    private String status;

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

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    public void setEnrolledTime(Date enrolledTime) 
    {
        this.enrolledTime = enrolledTime;
    }

    public Date getEnrolledTime() 
    {
        return enrolledTime;
    }

    public void setProgressRate(BigDecimal progressRate) 
    {
        this.progressRate = progressRate;
    }

    public BigDecimal getProgressRate() 
    {
        return progressRate;
    }

    public void setCompletedChapters(Long completedChapters) 
    {
        this.completedChapters = completedChapters;
    }

    public Long getCompletedChapters() 
    {
        return completedChapters;
    }

    public void setTotalChapters(Long totalChapters) 
    {
        this.totalChapters = totalChapters;
    }

    public Long getTotalChapters() 
    {
        return totalChapters;
    }

    public void setCompletedMaterials(Long completedMaterials) 
    {
        this.completedMaterials = completedMaterials;
    }

    public Long getCompletedMaterials() 
    {
        return completedMaterials;
    }

    public void setTotalMaterials(Long totalMaterials) 
    {
        this.totalMaterials = totalMaterials;
    }

    public Long getTotalMaterials() 
    {
        return totalMaterials;
    }

    public void setProgressUpdatedTime(Date progressUpdatedTime) 
    {
        this.progressUpdatedTime = progressUpdatedTime;
    }

    public Date getProgressUpdatedTime() 
    {
        return progressUpdatedTime;
    }

    public void setLastAccessed(Date lastAccessed) 
    {
        this.lastAccessed = lastAccessed;
    }

    public Date getLastAccessed() 
    {
        return lastAccessed;
    }

    public void setCompletionTime(Date completionTime) 
    {
        this.completionTime = completionTime;
    }

    public Date getCompletionTime() 
    {
        return completionTime;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("courseId", getCourseId())
            .append("enrolledTime", getEnrolledTime())
            .append("progressRate", getProgressRate())
            .append("completedChapters", getCompletedChapters())
            .append("totalChapters", getTotalChapters())
            .append("completedMaterials", getCompletedMaterials())
            .append("totalMaterials", getTotalMaterials())
            .append("progressUpdatedTime", getProgressUpdatedTime())
            .append("lastAccessed", getLastAccessed())
            .append("completionTime", getCompletionTime())
            .append("status", getStatus())
            .toString();
    }
}
