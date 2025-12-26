package com.teach.teachingsys.service;

import java.util.List;
import com.teach.teachingsys.domain.AssignmentSubmission;

/**
 * 作业提交Service接口
 * 
 * @author ruoyi
 * @date 2025-12-26
 */
public interface IAssignmentSubmissionService 
{
    /**
     * 查询作业提交
     * 
     * @param id 作业提交主键
     * @return 作业提交
     */
    public AssignmentSubmission selectAssignmentSubmissionById(Long id);

    /**
     * 查询作业提交列表
     * 
     * @param assignmentSubmission 作业提交
     * @return 作业提交集合
     */
    public List<AssignmentSubmission> selectAssignmentSubmissionList(AssignmentSubmission assignmentSubmission);

    /**
     * 新增作业提交
     * 
     * @param assignmentSubmission 作业提交
     * @return 结果
     */
    public int insertAssignmentSubmission(AssignmentSubmission assignmentSubmission);

    /**
     * 修改作业提交
     * 
     * @param assignmentSubmission 作业提交
     * @return 结果
     */
    public int updateAssignmentSubmission(AssignmentSubmission assignmentSubmission);

    /**
     * 批量删除作业提交
     * 
     * @param ids 需要删除的作业提交主键集合
     * @return 结果
     */
    public int deleteAssignmentSubmissionByIds(Long[] ids);

    /**
     * 删除作业提交信息
     * 
     * @param id 作业提交主键
     * @return 结果
     */
    public int deleteAssignmentSubmissionById(Long id);
}
