package com.teach.teachingsys.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业提交对象 assignment_submission
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class AssignmentSubmission extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 提交ID */
    private Long id;

    /** 作业ID */
    @Excel(name = "作业ID")
    private Long assignmentId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long userId;

    /** 尝试次数 */
    @Excel(name = "尝试次数")
    private Long attemptNumber;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submittedTime;

    /** 得分 */
    @Excel(name = "得分")
    private BigDecimal totalScore;

    /** 自动批改分数 */
    @Excel(name = "自动批改分数")
    private BigDecimal autoGradedScore;

    /** 教师反馈 */
    @Excel(name = "教师反馈")
    private String teacherFeedback;

    /** 批改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "批改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gradedTime;

    /** 批改教师ID */
    @Excel(name = "批改教师ID")
    private Long graderId;

    /** 提交内容 */
    @Excel(name = "提交内容")
    private String submissionData;

    /** 是否包含题目快照 */
    @Excel(name = "是否包含题目快照")
    private Integer hasQuestionSnapshot;

    /** 提交状态 */
    @Excel(name = "提交状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setAssignmentId(Long assignmentId) 
    {
        this.assignmentId = assignmentId;
    }

    public Long getAssignmentId() 
    {
        return assignmentId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setAttemptNumber(Long attemptNumber) 
    {
        this.attemptNumber = attemptNumber;
    }

    public Long getAttemptNumber() 
    {
        return attemptNumber;
    }

    public void setSubmittedTime(Date submittedTime) 
    {
        this.submittedTime = submittedTime;
    }

    public Date getSubmittedTime() 
    {
        return submittedTime;
    }

    public void setTotalScore(BigDecimal totalScore) 
    {
        this.totalScore = totalScore;
    }

    public BigDecimal getTotalScore() 
    {
        return totalScore;
    }

    public void setAutoGradedScore(BigDecimal autoGradedScore) 
    {
        this.autoGradedScore = autoGradedScore;
    }

    public BigDecimal getAutoGradedScore() 
    {
        return autoGradedScore;
    }

    public void setTeacherFeedback(String teacherFeedback) 
    {
        this.teacherFeedback = teacherFeedback;
    }

    public String getTeacherFeedback() 
    {
        return teacherFeedback;
    }

    public void setGradedTime(Date gradedTime) 
    {
        this.gradedTime = gradedTime;
    }

    public Date getGradedTime() 
    {
        return gradedTime;
    }

    public void setGraderId(Long graderId) 
    {
        this.graderId = graderId;
    }

    public Long getGraderId() 
    {
        return graderId;
    }

    public void setSubmissionData(String submissionData) 
    {
        this.submissionData = submissionData;
    }

    public String getSubmissionData() 
    {
        return submissionData;
    }

    public void setHasQuestionSnapshot(Integer hasQuestionSnapshot) 
    {
        this.hasQuestionSnapshot = hasQuestionSnapshot;
    }

    public Integer getHasQuestionSnapshot() 
    {
        return hasQuestionSnapshot;
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
            .append("assignmentId", getAssignmentId())
            .append("userId", getUserId())
            .append("attemptNumber", getAttemptNumber())
            .append("submittedTime", getSubmittedTime())
            .append("totalScore", getTotalScore())
            .append("autoGradedScore", getAutoGradedScore())
            .append("teacherFeedback", getTeacherFeedback())
            .append("gradedTime", getGradedTime())
            .append("graderId", getGraderId())
            .append("submissionData", getSubmissionData())
            .append("hasQuestionSnapshot", getHasQuestionSnapshot())
            .append("status", getStatus())
            .toString();
    }
}
