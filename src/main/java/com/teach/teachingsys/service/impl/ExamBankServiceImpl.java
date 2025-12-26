package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.ExamBankMapper;
import com.teach.teachingsys.domain.ExamBank;
import com.teach.teachingsys.service.IExamBankService;

/**
 * 考试题库关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class ExamBankServiceImpl implements IExamBankService 
{
    @Autowired
    private ExamBankMapper examBankMapper;

    /**
     * 查询考试题库关系
     * 
     * @param id 考试题库关系主键
     * @return 考试题库关系
     */
    @Override
    public ExamBank selectExamBankById(Long id)
    {
        return examBankMapper.selectExamBankById(id);
    }

    /**
     * 查询考试题库关系列表
     * 
     * @param examBank 考试题库关系
     * @return 考试题库关系
     */
    @Override
    public List<ExamBank> selectExamBankList(ExamBank examBank)
    {
        return examBankMapper.selectExamBankList(examBank);
    }

    /**
     * 新增考试题库关系
     * 
     * @param examBank 考试题库关系
     * @return 结果
     */
    @Override
    public int insertExamBank(ExamBank examBank)
    {
        return examBankMapper.insertExamBank(examBank);
    }

    /**
     * 修改考试题库关系
     * 
     * @param examBank 考试题库关系
     * @return 结果
     */
    @Override
    public int updateExamBank(ExamBank examBank)
    {
        return examBankMapper.updateExamBank(examBank);
    }

    /**
     * 批量删除考试题库关系
     * 
     * @param ids 需要删除的考试题库关系主键
     * @return 结果
     */
    @Override
    public int deleteExamBankByIds(Long[] ids)
    {
        return examBankMapper.deleteExamBankByIds(ids);
    }

    /**
     * 删除考试题库关系信息
     * 
     * @param id 考试题库关系主键
     * @return 结果
     */
    @Override
    public int deleteExamBankById(Long id)
    {
        return examBankMapper.deleteExamBankById(id);
    }
}
