package com.teach.teachingsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 考试题库关系对象 exam_bank
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class ExamBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    private Long id;

    /** 考试ID */
    @Excel(name = "考试ID")
    private Long examId;

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

    public void setExamId(Long examId) 
    {
        this.examId = examId;
    }

    public Long getExamId() 
    {
        return examId;
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
            .append("examId", getExamId())
            .append("questionId", getQuestionId())
            .append("questionOrder", getQuestionOrder())
            .toString();
    }
}
