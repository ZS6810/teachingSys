package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.ChoiceQuestion;

/**
 * 选择题Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface ChoiceQuestionMapper 
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
     * 删除选择题
     * 
     * @param id 选择题主键
     * @return 结果
     */
    public int deleteChoiceQuestionById(Long id);

    /**
     * 批量删除选择题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChoiceQuestionByIds(Long[] ids);
}
