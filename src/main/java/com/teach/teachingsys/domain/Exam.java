package com.teach.teachingsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试对象 exam
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class Exam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试ID */
    private Long id;

    /** 作业ID */
    @Excel(name = "作业ID")
    private Long assignmentId;

    /** 是否打乱题目顺序 */
    @Excel(name = "是否打乱题目顺序")
    private Integer shuffleQuestions;

    /** 是否显示正确答案 */
    @Excel(name = "是否显示正确答案")
    private Integer showCorrectAnswer;

    /** 是否允许回顾 */
    @Excel(name = "是否允许回顾")
    private Integer allowReview;

    /** 考试设置 */
    @Excel(name = "考试设置")
    private String settings;

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

    public void setShuffleQuestions(Integer shuffleQuestions) 
    {
        this.shuffleQuestions = shuffleQuestions;
    }

    public Integer getShuffleQuestions() 
    {
        return shuffleQuestions;
    }

    public void setShowCorrectAnswer(Integer showCorrectAnswer) 
    {
        this.showCorrectAnswer = showCorrectAnswer;
    }

    public Integer getShowCorrectAnswer() 
    {
        return showCorrectAnswer;
    }

    public void setAllowReview(Integer allowReview) 
    {
        this.allowReview = allowReview;
    }

    public Integer getAllowReview() 
    {
        return allowReview;
    }

    public void setSettings(String settings) 
    {
        this.settings = settings;
    }

    public String getSettings() 
    {
        return settings;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assignmentId", getAssignmentId())
            .append("shuffleQuestions", getShuffleQuestions())
            .append("showCorrectAnswer", getShowCorrectAnswer())
            .append("allowReview", getAllowReview())
            .append("settings", getSettings())
            .toString();
    }
}
