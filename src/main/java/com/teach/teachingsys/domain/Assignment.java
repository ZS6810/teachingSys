package com.teach.teachingsys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业对象 assignment
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Assignment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业ID */
    private Long id;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String title;

    /** 作业描述 */
    @Excel(name = "作业描述")
    private String description;

    /** 作业类型 */
    @Excel(name = "作业类型")
    private String assignmentType;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 总分 */
    @Excel(name = "总分")
    private BigDecimal totalScore;

    /** 及格分数 */
    @Excel(name = "及格分数")
    private BigDecimal passingScore;

    /** 时间限制(分钟) */
    @Excel(name = "时间限制(分钟)")
    private Long timeLimit;

    /** 最大尝试次数 */
    @Excel(name = "最大尝试次数")
    private Long maxAttempts;

    /** 提交次数 */
    @Excel(name = "提交次数")
    private Long submissionCount;

    /** 已批改次数 */
    @Excel(name = "已批改次数")
    private Long gradedCount;

    /** 平均分 */
    @Excel(name = "平均分")
    private BigDecimal averageScore;

    /** 最高分 */
    @Excel(name = "最高分")
    private BigDecimal highestScore;

    /** 最低分 */
    @Excel(name = "最低分")
    private BigDecimal lowestScore;

    /** 统计信息更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "统计信息更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date statsUpdatedTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setAssignmentType(String assignmentType) 
    {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentType() 
    {
        return assignmentType;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setDeadline(Date deadline) 
    {
        this.deadline = deadline;
    }

    public Date getDeadline() 
    {
        return deadline;
    }

    public void setTotalScore(BigDecimal totalScore) 
    {
        this.totalScore = totalScore;
    }

    public BigDecimal getTotalScore() 
    {
        return totalScore;
    }

    public void setPassingScore(BigDecimal passingScore) 
    {
        this.passingScore = passingScore;
    }

    public BigDecimal getPassingScore() 
    {
        return passingScore;
    }

    public void setTimeLimit(Long timeLimit) 
    {
        this.timeLimit = timeLimit;
    }

    public Long getTimeLimit() 
    {
        return timeLimit;
    }

    public void setMaxAttempts(Long maxAttempts) 
    {
        this.maxAttempts = maxAttempts;
    }

    public Long getMaxAttempts() 
    {
        return maxAttempts;
    }

    public void setSubmissionCount(Long submissionCount) 
    {
        this.submissionCount = submissionCount;
    }

    public Long getSubmissionCount() 
    {
        return submissionCount;
    }

    public void setGradedCount(Long gradedCount) 
    {
        this.gradedCount = gradedCount;
    }

    public Long getGradedCount() 
    {
        return gradedCount;
    }

    public void setAverageScore(BigDecimal averageScore) 
    {
        this.averageScore = averageScore;
    }

    public BigDecimal getAverageScore() 
    {
        return averageScore;
    }

    public void setHighestScore(BigDecimal highestScore) 
    {
        this.highestScore = highestScore;
    }

    public BigDecimal getHighestScore() 
    {
        return highestScore;
    }

    public void setLowestScore(BigDecimal lowestScore) 
    {
        this.lowestScore = lowestScore;
    }

    public BigDecimal getLowestScore() 
    {
        return lowestScore;
    }

    public void setStatsUpdatedTime(Date statsUpdatedTime) 
    {
        this.statsUpdatedTime = statsUpdatedTime;
    }

    public Date getStatsUpdatedTime() 
    {
        return statsUpdatedTime;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("title", getTitle())
            .append("description", getDescription())
            .append("assignmentType", getAssignmentType())
            .append("startTime", getStartTime())
            .append("deadline", getDeadline())
            .append("totalScore", getTotalScore())
            .append("passingScore", getPassingScore())
            .append("timeLimit", getTimeLimit())
            .append("maxAttempts", getMaxAttempts())
            .append("submissionCount", getSubmissionCount())
            .append("gradedCount", getGradedCount())
            .append("averageScore", getAverageScore())
            .append("highestScore", getHighestScore())
            .append("lowestScore", getLowestScore())
            .append("statsUpdatedTime", getStatsUpdatedTime())
            .append("status", getStatus())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
