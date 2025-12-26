package com.teach.teachingsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作业题库关系对象 assignment_bank
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class AssignmentBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long id;

    /** 作业ID */
    @Excel(name = "作业ID")
    private Long assignmentId;

    /** 题目ID */
    @Excel(name = "题目ID")
    private Long questionId;

    /** 题目顺序 */
    @Excel(name = "题目顺序")
    private Long questionOrder;

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

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }

    public void setQuestionOrder(Long questionOrder) 
    {
        this.questionOrder = questionOrder;
    }

    public Long getQuestionOrder() 
    {
        return questionOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assignmentId", getAssignmentId())
            .append("questionId", getQuestionId())
            .append("questionOrder", getQuestionOrder())
            .toString();
    }
}
