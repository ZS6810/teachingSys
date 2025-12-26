package com.teach.teachingsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 简答题对象 short_answer_question
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class ShortAnswerQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 简答题ID */
    private Long id;

    /** 题目ID */
    @Excel(name = "题目ID")
    private Long questionId;

    /** 参考答案 */
    @Excel(name = "参考答案")
    private String referenceAnswer;

    /** 答案长度限制 */
    @Excel(name = "答案长度限制")
    private Long answerLengthLimit;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }

    public void setReferenceAnswer(String referenceAnswer) 
    {
        this.referenceAnswer = referenceAnswer;
    }

    public String getReferenceAnswer() 
    {
        return referenceAnswer;
    }

    public void setAnswerLengthLimit(Long answerLengthLimit) 
    {
        this.answerLengthLimit = answerLengthLimit;
    }

    public Long getAnswerLengthLimit() 
    {
        return answerLengthLimit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("referenceAnswer", getReferenceAnswer())
            .append("answerLengthLimit", getAnswerLengthLimit())
            .toString();
    }
}
