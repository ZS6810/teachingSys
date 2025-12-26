package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Exam;

/**
 * 考试Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IExamService 
{
    /**
     * 查询考试
     * 
     * @param id 考试主键
     * @return 考试
     */
    public Exam selectExamById(Long id);

    /**
     * 查询考试列表
     * 
     * @param exam 考试
     * @return 考试集合
     */
    public List<Exam> selectExamList(Exam exam);

    /**
     * 新增考试
     * 
     * @param exam 考试
     * @return 结果
     */
    public int insertExam(Exam exam);

    /**
     * 修改考试
     * 
     * @param exam 考试
     * @return 结果
     */
    public int updateExam(Exam exam);

    /**
     * 批量删除考试
     * 
     * @param ids 需要删除的考试主键集合
     * @return 结果
     */
    public int deleteExamByIds(Long[] ids);

    /**
     * 删除考试信息
     * 
     * @param id 考试主键
     * @return 结果
     */
    public int deleteExamById(Long id);
}
