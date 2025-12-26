package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.ChoiceQuestionMapper;
import com.teach.teachingsys.domain.ChoiceQuestion;
import com.teach.teachingsys.service.IChoiceQuestionService;

/**
 * 选择题Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class ChoiceQuestionServiceImpl implements IChoiceQuestionService 
{
    @Autowired
    private ChoiceQuestionMapper choiceQuestionMapper;

    /**
     * 查询选择题
     * 
     * @param id 选择题主键
     * @return 选择题
     */
    @Override
    public ChoiceQuestion selectChoiceQuestionById(Long id)
    {
        return choiceQuestionMapper.selectChoiceQuestionById(id);
    }

    /**
     * 查询选择题列表
     * 
     * @param choiceQuestion 选择题
     * @return 选择题
     */
    @Override
    public List<ChoiceQuestion> selectChoiceQuestionList(ChoiceQuestion choiceQuestion)
    {
        return choiceQuestionMapper.selectChoiceQuestionList(choiceQuestion);
    }

    /**
     * 新增选择题
     * 
     * @param choiceQuestion 选择题
     * @return 结果
     */
    @Override
    public int insertChoiceQuestion(ChoiceQuestion choiceQuestion)
    {
        return choiceQuestionMapper.insertChoiceQuestion(choiceQuestion);
    }

    /**
     * 修改选择题
     * 
     * @param choiceQuestion 选择题
     * @return 结果
     */
    @Override
    public int updateChoiceQuestion(ChoiceQuestion choiceQuestion)
    {
        return choiceQuestionMapper.updateChoiceQuestion(choiceQuestion);
    }

    /**
     * 批量删除选择题
     * 
     * @param ids 需要删除的选择题主键
     * @return 结果
     */
    @Override
    public int deleteChoiceQuestionByIds(Long[] ids)
    {
        return choiceQuestionMapper.deleteChoiceQuestionByIds(ids);
    }

    /**
     * 删除选择题信息
     * 
     * @param id 选择题主键
     * @return 结果
     */
    @Override
    public int deleteChoiceQuestionById(Long id)
    {
        return choiceQuestionMapper.deleteChoiceQuestionById(id);
    }
}
