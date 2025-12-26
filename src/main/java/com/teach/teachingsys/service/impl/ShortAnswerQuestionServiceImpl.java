package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.ShortAnswerQuestionMapper;
import com.teach.teachingsys.domain.ShortAnswerQuestion;
import com.teach.teachingsys.service.IShortAnswerQuestionService;

/**
 * 简答题Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class ShortAnswerQuestionServiceImpl implements IShortAnswerQuestionService 
{
    @Autowired
    private ShortAnswerQuestionMapper shortAnswerQuestionMapper;

    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    @Override
    public ShortAnswerQuestion selectShortAnswerQuestionById(Long id)
    {
        return shortAnswerQuestionMapper.selectShortAnswerQuestionById(id);
    }

    /**
     * 查询简答题列表
     * 
     * @param shortAnswerQuestion 简答题
     * @return 简答题
     */
    @Override
    public List<ShortAnswerQuestion> selectShortAnswerQuestionList(ShortAnswerQuestion shortAnswerQuestion)
    {
        return shortAnswerQuestionMapper.selectShortAnswerQuestionList(shortAnswerQuestion);
    }

    /**
     * 新增简答题
     * 
     * @param shortAnswerQuestion 简答题
     * @return 结果
     */
    @Override
    public int insertShortAnswerQuestion(ShortAnswerQuestion shortAnswerQuestion)
    {
        return shortAnswerQuestionMapper.insertShortAnswerQuestion(shortAnswerQuestion);
    }

    /**
     * 修改简答题
     * 
     * @param shortAnswerQuestion 简答题
     * @return 结果
     */
    @Override
    public int updateShortAnswerQuestion(ShortAnswerQuestion shortAnswerQuestion)
    {
        return shortAnswerQuestionMapper.updateShortAnswerQuestion(shortAnswerQuestion);
    }

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的简答题主键
     * @return 结果
     */
    @Override
    public int deleteShortAnswerQuestionByIds(Long[] ids)
    {
        return shortAnswerQuestionMapper.deleteShortAnswerQuestionByIds(ids);
    }

    /**
     * 删除简答题信息
     * 
     * @param id 简答题主键
     * @return 结果
     */
    @Override
    public int deleteShortAnswerQuestionById(Long id)
    {
        return shortAnswerQuestionMapper.deleteShortAnswerQuestionById(id);
    }
}
