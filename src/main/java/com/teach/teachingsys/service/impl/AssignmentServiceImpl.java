package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.AssignmentMapper;
import com.teach.teachingsys.domain.Assignment;
import com.teach.teachingsys.service.IAssignmentService;

/**
 * 作业Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class AssignmentServiceImpl implements IAssignmentService 
{
    @Autowired
    private AssignmentMapper assignmentMapper;

    /**
     * 查询作业
     * 
     * @param id 作业主键
     * @return 作业
     */
    @Override
    public Assignment selectAssignmentById(Long id)
    {
        return assignmentMapper.selectAssignmentById(id);
    }

    /**
     * 查询作业列表
     * 
     * @param assignment 作业
     * @return 作业
     */
    @Override
    public List<Assignment> selectAssignmentList(Assignment assignment)
    {
        return assignmentMapper.selectAssignmentList(assignment);
    }

    /**
     * 新增作业
     * 
     * @param assignment 作业
     * @return 结果
     */
    @Override
    public int insertAssignment(Assignment assignment)
    {
        return assignmentMapper.insertAssignment(assignment);
    }

    /**
     * 修改作业
     * 
     * @param assignment 作业
     * @return 结果
     */
    @Override
    public int updateAssignment(Assignment assignment)
    {
        return assignmentMapper.updateAssignment(assignment);
    }

    /**
     * 批量删除作业
     * 
     * @param ids 需要删除的作业主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentByIds(Long[] ids)
    {
        return assignmentMapper.deleteAssignmentByIds(ids);
    }

    /**
     * 删除作业信息
     * 
     * @param id 作业主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentById(Long id)
    {
        return assignmentMapper.deleteAssignmentById(id);
    }
}
