package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.Learningprogress;

/**
 * 学习进度Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface ILearningprogressService 
{
    /**
     * 查询学习进度
     * 
     * @param id 学习进度主键
     * @return 学习进度
     */
    public Learningprogress selectLearningprogressById(Long id);

    /**
     * 查询学习进度列表
     * 
     * @param learningprogress 学习进度
     * @return 学习进度集合
     */
    public List<Learningprogress> selectLearningprogressList(Learningprogress learningprogress);

    /**
     * 新增学习进度
     * 
     * @param learningprogress 学习进度
     * @return 结果
     */
    public int insertLearningprogress(Learningprogress learningprogress);

    /**
     * 修改学习进度
     * 
     * @param learningprogress 学习进度
     * @return 结果
     */
    public int updateLearningprogress(Learningprogress learningprogress);

    /**
     * 批量删除学习进度
     * 
     * @param ids 需要删除的学习进度主键集合
     * @return 结果
     */
    public int deleteLearningprogressByIds(Long[] ids);

    /**
     * 删除学习进度信息
     * 
     * @param id 学习进度主键
     * @return 结果
     */
    public int deleteLearningprogressById(Long id);
}
