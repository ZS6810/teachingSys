package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.QuestionbankMapper;
import com.teach.teachingsys.domain.Questionbank;
import com.teach.teachingsys.service.IQuestionbankService;

/**
 * 题库Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class QuestionbankServiceImpl implements IQuestionbankService 
{
    @Autowired
    private QuestionbankMapper questionbankMapper;

    /**
     * 查询题库
     * 
     * @param id 题库主键
     * @return 题库
     */
    @Override
    public Questionbank selectQuestionbankById(Long id)
    {
        return questionbankMapper.selectQuestionbankById(id);
    }

    /**
     * 查询题库列表
     * 
     * @param questionbank 题库
     * @return 题库
     */
    @Override
    public List<Questionbank> selectQuestionbankList(Questionbank questionbank)
    {
        return questionbankMapper.selectQuestionbankList(questionbank);
    }

    /**
     * 新增题库
     * 
     * @param questionbank 题库
     * @return 结果
     */
    @Override
    public int insertQuestionbank(Questionbank questionbank)
    {
        return questionbankMapper.insertQuestionbank(questionbank);
    }

    /**
     * 修改题库
     * 
     * @param questionbank 题库
     * @return 结果
     */
    @Override
    public int updateQuestionbank(Questionbank questionbank)
    {
        return questionbankMapper.updateQuestionbank(questionbank);
    }

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的题库主键
     * @return 结果
     */
    @Override
    public int deleteQuestionbankByIds(Long[] ids)
    {
        return questionbankMapper.deleteQuestionbankByIds(ids);
    }

    /**
     * 删除题库信息
     * 
     * @param id 题库主键
     * @return 结果
     */
    @Override
    public int deleteQuestionbankById(Long id)
    {
        return questionbankMapper.deleteQuestionbankById(id);
    }
}
