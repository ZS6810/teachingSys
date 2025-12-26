package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.ExamMapper;
import com.teach.teachingsys.domain.Exam;
import com.teach.teachingsys.service.IExamService;

/**
 * 考试Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class ExamServiceImpl implements IExamService 
{
    @Autowired
    private ExamMapper examMapper;

    /**
     * 查询考试
     * 
     * @param id 考试主键
     * @return 考试
     */
    @Override
    public Exam selectExamById(Long id)
    {
        return examMapper.selectExamById(id);
    }

    /**
     * 查询考试列表
     * 
     * @param exam 考试
     * @return 考试
     */
    @Override
    public List<Exam> selectExamList(Exam exam)
    {
        return examMapper.selectExamList(exam);
    }

    /**
     * 新增考试
     * 
     * @param exam 考试
     * @return 结果
     */
    @Override
    public int insertExam(Exam exam)
    {
        return examMapper.insertExam(exam);
    }

    /**
     * 修改考试
     * 
     * @param exam 考试
     * @return 结果
     */
    @Override
    public int updateExam(Exam exam)
    {
        return examMapper.updateExam(exam);
    }

    /**
     * 批量删除考试
     * 
     * @param ids 需要删除的考试主键
     * @return 结果
     */
    @Override
    public int deleteExamByIds(Long[] ids)
    {
        return examMapper.deleteExamByIds(ids);
    }

    /**
     * 删除考试信息
     * 
     * @param id 考试主键
     * @return 结果
     */
    @Override
    public int deleteExamById(Long id)
    {
        return examMapper.deleteExamById(id);
    }
}
