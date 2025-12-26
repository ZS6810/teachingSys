package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.ChoiceQuestion;

/**
 * 选择题Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IChoiceQuestionService 
{
    /**
     * 查询选择题
     * 
     * @param id 选择题主键
     * @return 选择题
     */
    public ChoiceQuestion selectChoiceQuestionById(Long id);

    /**
     * 查询选择题列表
     * 
     * @param choiceQuestion 选择题
     * @return 选择题集合
     */
    public List<ChoiceQuestion> selectChoiceQuestionList(ChoiceQuestion choiceQuestion);

    /**
     * 新增选择题
     * 
     * @param choiceQuestion 选择题
     * @return 结果
     */
    public int insertChoiceQuestion(ChoiceQuestion choiceQuestion);

    /**
     * 修改选择题
     * 
     * @param choiceQuestion 选择题
     * @return 结果
     */
    public int updateChoiceQuestion(ChoiceQuestion choiceQuestion);

    /**
     * 批量删除选择题
     * 
     * @param ids 需要删除的选择题主键集合
     * @return 结果
     */
    public int deleteChoiceQuestionByIds(Long[] ids);

    /**
     * 删除选择题信息
     * 
     * @param id 选择题主键
     * @return 结果
     */
    public int deleteChoiceQuestionById(Long id);
}
