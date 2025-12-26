package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.LearningprogressMapper;
import com.teach.teachingsys.domain.Learningprogress;
import com.teach.teachingsys.service.ILearningprogressService;

/**
 * 学习进度Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class LearningprogressServiceImpl implements ILearningprogressService 
{
    @Autowired
    private LearningprogressMapper learningprogressMapper;

    /**
     * 查询学习进度
     * 
     * @param id 学习进度主键
     * @return 学习进度
     */
    @Override
    public Learningprogress selectLearningprogressById(Long id)
    {
        return learningprogressMapper.selectLearningprogressById(id);
    }

    /**
     * 查询学习进度列表
     * 
     * @param learningprogress 学习进度
     * @return 学习进度
     */
    @Override
    public List<Learningprogress> selectLearningprogressList(Learningprogress learningprogress)
    {
        return learningprogressMapper.selectLearningprogressList(learningprogress);
    }

    /**
     * 新增学习进度
     * 
     * @param learningprogress 学习进度
     * @return 结果
     */
    @Override
    public int insertLearningprogress(Learningprogress learningprogress)
    {
        return learningprogressMapper.insertLearningprogress(learningprogress);
    }

    /**
     * 修改学习进度
     * 
     * @param learningprogress 学习进度
     * @return 结果
     */
    @Override
    public int updateLearningprogress(Learningprogress learningprogress)
    {
        return learningprogressMapper.updateLearningprogress(learningprogress);
    }

    /**
     * 批量删除学习进度
     * 
     * @param ids 需要删除的学习进度主键
     * @return 结果
     */
    @Override
    public int deleteLearningprogressByIds(Long[] ids)
    {
        return learningprogressMapper.deleteLearningprogressByIds(ids);
    }

    /**
     * 删除学习进度信息
     * 
     * @param id 学习进度主键
     * @return 结果
     */
    @Override
    public int deleteLearningprogressById(Long id)
    {
        return learningprogressMapper.deleteLearningprogressById(id);
    }
}
