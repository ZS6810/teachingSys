package com.teach.teachingsys.mapper;

import java.util.List;
import com.teach.teachingsys.domain.ExamBank;

/**
 * 考试题库关系Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface ExamBankMapper 
{
    /**
     * 查询考试题库关系
     * 
     * @param id 考试题库关系主键
     * @return 考试题库关系
     */
    public ExamBank selectExamBankById(Long id);

    /**
     * 查询考试题库关系列表
     * 
     * @param examBank 考试题库关系
     * @return 考试题库关系集合
     */
    public List<ExamBank> selectExamBankList(ExamBank examBank);

    /**
     * 新增考试题库关系
     * 
     * @param examBank 考试题库关系
     * @return 结果
     */
    public int insertExamBank(ExamBank examBank);

    /**
     * 修改考试题库关系
     * 
     * @param examBank 考试题库关系
     * @return 结果
     */
    public int updateExamBank(ExamBank examBank);

    /**
     * 删除考试题库关系
     * 
     * @param id 考试题库关系主键
     * @return 结果
     */
    public int deleteExamBankById(Long id);

    /**
     * 批量删除考试题库关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteExamBankByIds(Long[] ids);
}
