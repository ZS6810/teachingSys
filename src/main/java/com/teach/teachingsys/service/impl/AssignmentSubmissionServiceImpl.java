package com.teach.teachingsys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teach.teachingsys.mapper.AssignmentSubmissionMapper;
import com.teach.teachingsys.domain.AssignmentSubmission;
import com.teach.teachingsys.service.IAssignmentSubmissionService;

/**
 * 作业提交Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
@Service
public class AssignmentSubmissionServiceImpl implements IAssignmentSubmissionService 
{
    @Autowired
    private AssignmentSubmissionMapper assignmentSubmissionMapper;

    /**
     * 查询作业提交
     * 
     * @param id 作业提交主键
     * @return 作业提交
     */
    @Override
    public AssignmentSubmission selectAssignmentSubmissionById(Long id)
    {
        return assignmentSubmissionMapper.selectAssignmentSubmissionById(id);
    }

    /**
     * 查询作业提交列表
     * 
     * @param assignmentSubmission 作业提交
     * @return 作业提交
     */
    @Override
    public List<AssignmentSubmission> selectAssignmentSubmissionList(AssignmentSubmission assignmentSubmission)
    {
        return assignmentSubmissionMapper.selectAssignmentSubmissionList(assignmentSubmission);
    }

    /**
     * 新增作业提交
     * 
     * @param assignmentSubmission 作业提交
     * @return 结果
     */
    @Override
    public int insertAssignmentSubmission(AssignmentSubmission assignmentSubmission)
    {
        return assignmentSubmissionMapper.insertAssignmentSubmission(assignmentSubmission);
    }

    /**
     * 修改作业提交
     * 
     * @param assignmentSubmission 作业提交
     * @return 结果
     */
    @Override
    public int updateAssignmentSubmission(AssignmentSubmission assignmentSubmission)
    {
        return assignmentSubmissionMapper.updateAssignmentSubmission(assignmentSubmission);
    }

    /**
     * 批量删除作业提交
     * 
     * @param ids 需要删除的作业提交主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentSubmissionByIds(Long[] ids)
    {
        return assignmentSubmissionMapper.deleteAssignmentSubmissionByIds(ids);
    }

    /**
     * 删除作业提交信息
     * 
     * @param id 作业提交主键
     * @return 结果
     */
    @Override
    public int deleteAssignmentSubmissionById(Long id)
    {
        return assignmentSubmissionMapper.deleteAssignmentSubmissionById(id);
    }
}
