package com.teach.teachingsys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 选择题对象 choice_question
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public class ChoiceQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选择题ID */
    private Long id;

    /** 题目ID */
    @Excel(name = "题目ID")
    private Long questionId;

    /** 选项A */
    @Excel(name = "选项A")
    private String optionA;

    /** 选项B */
    @Excel(name = "选项B")
    private String optionB;

    /** 选项C */
    @Excel(name = "选项C")
    private String optionC;

    /** 选项D */
    @Excel(name = "选项D")
    private String optionD;

    /** 选项E */
    @Excel(name = "选项E")
    private String optionE;

    /** 选项F */
    @Excel(name = "选项F")
    private String optionF;

    /** 正确答案(如A,B,AB等) */
    @Excel(name = "正确答案(如A,B,AB等)")
    private String correctAnswer;

    /** 是否多选题 */
    @Excel(name = "是否多选题")
    private Integer isMultiple;

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

    public void setOptionA(String optionA) 
    {
        this.optionA = optionA;
    }

    public String getOptionA() 
    {
        return optionA;
    }

    public void setOptionB(String optionB) 
    {
        this.optionB = optionB;
    }

    public String getOptionB() 
    {
        return optionB;
    }

    public void setOptionC(String optionC) 
    {
        this.optionC = optionC;
    }

    public String getOptionC() 
    {
        return optionC;
    }

    public void setOptionD(String optionD) 
    {
        this.optionD = optionD;
    }

    public String getOptionD() 
    {
        return optionD;
    }

    public void setOptionE(String optionE) 
    {
        this.optionE = optionE;
    }

    public String getOptionE() 
    {
        return optionE;
    }

    public void setOptionF(String optionF) 
    {
        this.optionF = optionF;
    }

    public String getOptionF() 
    {
        return optionF;
    }

    public void setCorrectAnswer(String correctAnswer) 
    {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() 
    {
        return correctAnswer;
    }

    public void setIsMultiple(Integer isMultiple) 
    {
        this.isMultiple = isMultiple;
    }

    public Integer getIsMultiple() 
    {
        return isMultiple;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("optionA", getOptionA())
            .append("optionB", getOptionB())
            .append("optionC", getOptionC())
            .append("optionD", getOptionD())
            .append("optionE", getOptionE())
            .append("optionF", getOptionF())
            .append("correctAnswer", getCorrectAnswer())
            .append("isMultiple", getIsMultiple())
            .toString();
    }
}
