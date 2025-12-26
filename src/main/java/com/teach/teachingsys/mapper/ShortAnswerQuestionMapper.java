package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.ShortAnswerQuestion;

/**
 * 简答题Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface ShortAnswerQuestionMapper 
{
    /**
     * 查询简答题
     * 
     * @param id 简答题主键
     * @return 简答题
     */
    public ShortAnswerQuestion selectShortAnswerQuestionById(Long id);

    /**
     * 查询简答题列表
     * 
     * @param shortAnswerQuestion 简答题
     * @return 简答题集合
     */
    public List<ShortAnswerQuestion> selectShortAnswerQuestionList(ShortAnswerQuestion shortAnswerQuestion);

    /**
     * 新增简答题
     * 
     * @param shortAnswerQuestion 简答题
     * @return 结果
     */
    public int insertShortAnswerQuestion(ShortAnswerQuestion shortAnswerQuestion);

    /**
     * 修改简答题
     * 
     * @param shortAnswerQuestion 简答题
     * @return 结果
     */
    public int updateShortAnswerQuestion(ShortAnswerQuestion shortAnswerQuestion);

    /**
     * 删除简答题
     * 
     * @param id 简答题主键
     * @return 结果
     */
    public int deleteShortAnswerQuestionById(Long id);

    /**
     * 批量删除简答题
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShortAnswerQuestionByIds(Long[] ids);
}
